package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.services.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/revenue")
public class RevenueController {

    @Autowired
    private IRevenueService revenueService;

    @GetMapping()
    public ResponseEntity<Map<String, Double>> getRevenueSummary() {
        Map<String, Double> revenueSummary = new HashMap<>();
        revenueSummary.put("today", revenueService.calculateTodayRevenue());
        revenueSummary.put("thisMonth", revenueService.calculateThisMonthRevenue());
        revenueSummary.put("thisYear", revenueService.calculateThisYearRevenue());

        return ResponseEntity.ok(revenueSummary);
    }
}

