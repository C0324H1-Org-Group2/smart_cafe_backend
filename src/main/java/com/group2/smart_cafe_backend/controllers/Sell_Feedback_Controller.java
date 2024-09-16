package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.dtos.BillDTO;
import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.services.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class Sell_Feedback_Controller {

    @Autowired
    private ISellService sellService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/feedbacks")
    public ResponseEntity<List<Feedback>> findAllFeedback() {
        List<Feedback> feedbackList = sellService.findAllFeedback();
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }


    @GetMapping("/feedbacks/{date}")
    public ResponseEntity<List<Feedback>> findFeedbackByDate(@PathVariable LocalDate date) {
        List<Feedback> feedbackList = sellService.findFeedbackByDate(date);
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }

    @GetMapping("/table")
    public ResponseEntity<List<Tables>> findAllTable() {
        List<Tables> tablesList = sellService.findAllTable();

        messagingTemplate.convertAndSend("/topic/client/order", tablesList);
        return new ResponseEntity<>(tablesList, HttpStatus.OK);
    }

    @GetMapping("/bills/{table_id}")
    public ResponseEntity<List<BillDTO>> findBillByTableId(@PathVariable Long table_id) {
        List<BillDTO> billDTOS = sellService.findBillByTableId(table_id);
        return new ResponseEntity<>(billDTOS, HttpStatus.OK);
    }

    @PatchMapping("/bills/delete/{tableId}")
    public ResponseEntity<Boolean> changeStatusBillByTableId(@PathVariable Long tableId) {
        boolean statusBills = sellService.updateBillStatus(tableId);
        boolean statusTable = sellService.updateTableStatus(tableId);
        boolean isSuccess;
        if(statusTable == true && statusBills == true){
             isSuccess = true;
        }else {
            isSuccess = false;
        }
        return new ResponseEntity<>(isSuccess, HttpStatus.OK);
    }

    @PatchMapping("/table/employee/{tableId}")
    public void setStatusEmployee(@PathVariable Long tableId){
        Tables tables = sellService.setStatusEmployee(tableId);
        messagingTemplate.convertAndSend("/topic/client/callEmployee", tables);
    }

    @PatchMapping("table/order/{tableId}")
    public void setStatusOrder(@PathVariable Long tableId){
        sellService.setStatusOrder(tableId);
    }


}
