package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.*;
import com.group2.smart_cafe_backend.models.emum.BillStatus;
import com.group2.smart_cafe_backend.repositories.*;
import com.group2.smart_cafe_backend.services.impl.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/client/payment")
public class VNPayController {

    @Autowired
    private VNPayService vnPayService;

    @Autowired
    private IBillRepository billRepository;

    @Autowired
    private IBillDetailRepository billDetailRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IServiceRepository serviceRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(
            @RequestBody Map<String, Object> request,
            HttpServletRequest httpRequest) {
        try {
            long amount = Long.parseLong(request.get("amount").toString());
            String orderInfo = request.getOrDefault("orderInfo",
                    "Thanh toan don hang Hương Coffee").toString();
            // VNPay txnRef max 100 chars, alphanumeric only
            String txnRef = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
            String ipAddr = getClientIp(httpRequest);

            String paymentUrl = vnPayService.createPaymentUrl(amount, orderInfo, txnRef, ipAddr);

            Map<String, String> response = new HashMap<>();
            response.put("paymentUrl", paymentUrl);
            response.put("txnRef", txnRef);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi tạo thanh toán: " + e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> params) {
        try {
            boolean valid = vnPayService.verifyPayment(params);
            Map<String, Object> response = new HashMap<>();
            response.put("valid", valid);
            response.put("responseCode", params.getOrDefault("vnp_ResponseCode", "99"));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi xác thực: " + e.getMessage());
        }
    }

    @PostMapping("/confirm-order")
    public ResponseEntity<?> confirmOrder(@RequestBody Map<String, Object> payload) {
        try {
            List<Map<String, Object>> cartItems = (List<Map<String, Object>>) payload.get("cartItems");
            Map<String, String> customerInfo = (Map<String, String>) payload.get("customerInfo");

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User currentUser = userRepository.findByUsername(username);

            // 1. Create new Bill
            Bill bill = new Bill();
            bill.setCreator(currentUser);
            bill.setDateCreated(LocalDateTime.now());
            bill.setStatus(BillStatus.completed); // Already paid via VNPay
            
            // Auto generate code
            String lastCode = billRepository.findMaxBillCode();
            int nextId = 1;
            if (lastCode != null && lastCode.startsWith("HD-")) {
                try {
                    nextId = Integer.parseInt(lastCode.substring(3)) + 1;
                } catch (Exception ignore) {}
            }
            bill.setCode(String.format("HD-%05d", nextId));
            
            // For online order, we might not have a table, or use a default 'Online' table
            // For now, keep it null or set a default if available
            bill.setTable(null); 
            
            if (customerInfo != null) {
                bill.setCustomerName(customerInfo.get("fullName"));
                bill.setPhone(customerInfo.get("phone"));
                bill.setAddress(customerInfo.get("address"));
            }

            Bill savedBill = billRepository.save(bill);

            // 2. Save Bill Details
            for (Map<String, Object> item : cartItems) {
                Long serviceId = Long.parseLong(item.get("serviceId").toString());
                int quantity = Integer.parseInt(item.get("quantity").toString());

                com.group2.smart_cafe_backend.models.Service service = 
                        serviceRepository.findById(serviceId).orElse(null);
                
                if (service != null) {
                    BillDetail detail = new BillDetail();
                    detail.setBill(savedBill);
                    detail.setService(service);
                    detail.setQuantity(quantity);
                    detail.setOrder(true);
                    billDetailRepository.save(detail);
                }
            }

            // 3. Notify Admin via WebSocket
            // Format expected by SellNotification.js: { tableId, code }
            Map<String, Object> notification = new HashMap<>();
            notification.put("isOnline", true);
            notification.put("code", savedBill.getCode());
            notification.put("tableId", -1L); // Use -1 as a flag for online order
            
            messagingTemplate.convertAndSend("/topic/admin/sell/order", notification);

            return ResponseEntity.ok(Map.of("message", "Đã lưu đơn hàng thành công", "billCode", savedBill.getCode()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Lỗi lưu đơn hàng: " + e.getMessage());
        }
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
