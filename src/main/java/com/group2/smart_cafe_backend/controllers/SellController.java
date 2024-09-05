package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SellController {

    @Autowired
    private IService service;

    @GetMapping("/tables")
    public ResponseEntity<List<Tables>> findAllTable() {
        List<Tables> tablesList = service.findAllTable();
        if (tablesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tablesList, HttpStatus.OK);
        }
    }

    @GetMapping("/feedbacks")
    public ResponseEntity<List<Feedback>> findAllFeedback(){
        List<Feedback> feedbackList = service.findAllFeedback();
        if (feedbackList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(feedbackList,HttpStatus.OK);
        }
    }



}
