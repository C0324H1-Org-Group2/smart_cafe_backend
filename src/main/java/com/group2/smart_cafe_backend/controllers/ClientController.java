package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.dtos.FeedbackRequestDTO;
import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.BillDetail;
import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.repositories.IFeedbackRepository;
import com.group2.smart_cafe_backend.services.IBillDetailService;
import com.group2.smart_cafe_backend.services.IBillService;
import com.group2.smart_cafe_backend.services.IFeedbackService;
import com.group2.smart_cafe_backend.services.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ITableService tableService;

    @Autowired
    private IBillService billService;

    @Autowired
    private IBillDetailService billDetailService;

    @Autowired
    private IFeedbackService feedbackService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    // Endpoint để cập nhật trạng thái bảng thành false
    @PatchMapping("/tables/{id}/status_createBill")
    public Bill updateTableStatusAndCreateBill(@PathVariable Long id) {
        // Cập nhật trạng thái bảng thành false
        Tables table = tableService.updateTableStatus(id);

        messagingTemplate.convertAndSend("/topic/admin/sell/order", table);

        // Tạo hóa đơn mới
        return billService.createBill(table);
    }

    @PatchMapping("/tables/{id}/status")
    public Tables updateTableStatus(@PathVariable Long id) {
        // Cập nhật trạng thái bảng
        Tables table = tableService.updateTableStatus1(id);
        messagingTemplate.convertAndSend("/topic/admin/sell/pay", table);
        return table;
    }

    @PatchMapping("/tables/{id}/statusBill")
    public Tables updateTableStatusBill(@PathVariable Long id) {
        // Cập nhật trạng thái bảng
        Tables tables = tableService.updateTableStatusBill(id);
        messagingTemplate.convertAndSend("/topic/admin/sell/order", tables);
        return tables;
    }

    @PostMapping("/bill-details/order")
    public List<BillDetail> saveBillDetails(@RequestBody List<BillDetail> orderDetails) {
        return billDetailService.saveBillDetails(orderDetails);
    }

    @GetMapping("/tables")
    public List<Tables> getAllTables() {
        return tableService.getAllTablesByClient();
    }

    @PatchMapping("/billDetails/update-bill")
    public List<BillDetail> updateProductsWithBill(@RequestBody List<BillDetail> billDetails) {
        return billDetailService.updateBillDetailsWithBill(billDetails);
    }

//    @PostMapping("/feedback_client")
//    public Feedback submitFeedback(@RequestBody FeedbackRequestDTO feedbackRequest) {
//        Feedback feedback = feedbackService.saveFeedback(feedbackRequest.getEmail(),feedbackRequest.getMessage(),feedbackRequest.getImageUrl());
//        messagingTemplate.convertAndSend("/topic/admin/feedback", feedback);
//        return feedback;
//    }

    @PostMapping("/feedback_client")
    public ResponseEntity<Feedback> submitFeedback(
            @RequestParam("email") String email,
            @RequestParam("message") String message,
            @RequestPart("imageFile") MultipartFile imageFile) {
        Feedback feedback = feedbackService.saveFeedback(email,message,imageFile);
        messagingTemplate.convertAndSend("/topic/admin/feedback", feedback);
        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }

    @GetMapping("/tables/{id}/check-is-bill")
    public boolean checkIsBill(@PathVariable Long id) {
        return tableService.isTableBill(id);
    }

    @GetMapping("/tables/{id}/check-is-call")
    public boolean checkIsCall(@PathVariable Long id) {
        return tableService.isTableCall(id);
    }

    @PostMapping("/tables/{id}/callEmployee")
    public Tables callEmployee(@PathVariable Long id){
        Tables table = tableService.callEmployee(id);
        messagingTemplate.convertAndSend("/topic/admin/sell/callEmployee", table);
        return table;
    }
}
