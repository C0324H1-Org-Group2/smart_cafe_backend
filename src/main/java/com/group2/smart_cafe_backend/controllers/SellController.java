package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.services.ISellService;
import com.group2.smart_cafe_backend.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SellController {

    @Autowired
    private ISellService sellService;


    @GetMapping("/feedbacks")
    public ResponseEntity<List<Feedback>> findAllFeedback() {
        List<Feedback> feedbackList = sellService.findAllFeedback();
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(feedbackList, HttpStatus.OK);
        }
    }

    @GetMapping("/feedbacks/{date}")
    public ResponseEntity<Feedback> findFeedbackByDate(@PathVariable LocalDate date) {

        Feedback feedback = sellService.findFeedbackByDate(date);
        if (feedback == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        }
    }

    @GetMapping("/tables")
    public ResponseEntity<List<Tables>> findAllTable() {
        List<Tables> tablesList = sellService.findAllTable();
        if (tablesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tablesList, HttpStatus.OK);
        }
    }

    @GetMapping("/bills/{table_id}")
    public ResponseEntity<Bill> findBillByTableId(@PathVariable Long table_id) {
        Optional<Bill> bill = sellService.findBillByTableId(table_id);
        if (bill.isPresent()) {
            return new ResponseEntity<>(bill.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
