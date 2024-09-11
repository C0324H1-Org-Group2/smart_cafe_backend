package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.BillDetail;
import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.services.IBillDetailService;
import com.group2.smart_cafe_backend.services.IBillService;
import com.group2.smart_cafe_backend.services.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // Endpoint để lấy một bảng ngẫu nhiên có trạng thái isOn = true
    @GetMapping("/table/random")
    public Tables getRandomAvailableTable() {
        return tableService.getRandomAvailableTable();
    }

    // Endpoint để cập nhật trạng thái bảng thành false
    @PatchMapping("/tables/{id}/status")
    public Bill updateTableStatusAndCreateBill(@PathVariable Long id) {
        // Cập nhật trạng thái bảng thành false
        Tables table = tableService.updateTableStatus(id);

        // Tạo hóa đơn mới
        return billService.createBill(table);
    }

    @PostMapping("/bill-details/order")
    public List<BillDetail> saveBillDetails(@RequestBody List<BillDetail> orderDetails) {
        return billDetailService.saveBillDetails(orderDetails);
    }

}
