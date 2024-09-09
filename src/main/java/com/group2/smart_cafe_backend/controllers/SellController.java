package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.dtos.BillDTO;
import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.services.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class SellController {

    @Autowired
    private ISellService sellService;

    @GetMapping("/feedbacks")
    public ResponseEntity<List<Feedback>> findAllFeedback() {
        List<Feedback> feedbackList = sellService.findAllFeedback();
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }


//    @GetMapping("/feedbacks/{date}")
//    public ResponseEntity<List<Feedback>> findFeedbackByDate(@PathVariable LocalDate date) {
//        List<Feedback> feedbackList = sellService.findFeedbackByDate(date);
//        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
//    }

    @GetMapping("/tables")
    public ResponseEntity<List<Tables>> findAllTable() {
        List<Tables> tablesList = sellService.findAllTable();
        return new ResponseEntity<>(tablesList, HttpStatus.OK);
    }

    @GetMapping("/bills/{table_id}")
    public ResponseEntity<List<BillDTO>> findBillByTableId(@PathVariable Long table_id) {
        List<BillDTO> billDetails = sellService.findBillByTableId(table_id);
        if (billDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(billDetails, HttpStatus.OK);
    }


}
