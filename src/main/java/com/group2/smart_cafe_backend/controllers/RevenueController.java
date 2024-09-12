package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.dtos.MonthRevenueDTO;
import com.group2.smart_cafe_backend.dtos.ServiceRevenueDTO;
import com.group2.smart_cafe_backend.dtos.TopSellServiceDTO;
import com.group2.smart_cafe_backend.services.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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
        revenueSummary.put("yesterday", revenueService.calculateYesterdayRevenue());
        revenueSummary.put("thisMonth", revenueService.calculateThisMonthRevenue());
        revenueSummary.put("lastMonth", revenueService.calculateLastMonthRevenue());
        revenueSummary.put("thisYear", revenueService.calculateThisYearRevenue());
        revenueSummary.put("lastYear", revenueService.calculateLastYearRevenue());
        return ResponseEntity.ok(revenueSummary);
    }

    @GetMapping("/month-in-year")
    public ResponseEntity<Map<String, List<MonthRevenueDTO>>> getMonthRevenueInYear() {
        Map<String, List<MonthRevenueDTO>> monthRevenueInYear = new HashMap<>();
        monthRevenueInYear.put("thisYear", revenueService.getMonthlyRevenueInCurrentYear());
        monthRevenueInYear.put("lastYear", revenueService.getMonthlyRevenueInLastYear());
        return ResponseEntity.ok(monthRevenueInYear);
    }

    @GetMapping("/service")
    public ResponseEntity<Map<String, List<ServiceRevenueDTO>>> getRevenueService() {
        Map<String, List<ServiceRevenueDTO>> serviceRevenueInYear = new HashMap<>();
        serviceRevenueInYear.put("thisYear", revenueService.getRevenueServiceInCurrentYear());
        serviceRevenueInYear.put("lastYear", revenueService.getRevenueServiceInLastYear());
        return ResponseEntity.ok(serviceRevenueInYear);
    }

    @GetMapping("/top-service")
    public ResponseEntity<?> getTopSellService(@RequestParam(value = "year", defaultValue = "current") String year) {
        // Xử lý năm được truyền vào
        int yearValue;
        if (year.equalsIgnoreCase("lastYear")) {
            yearValue = java.time.LocalDate.now().getYear() - 1;
        } else if (year.equalsIgnoreCase("current")) {
            yearValue = java.time.LocalDate.now().getYear();
        } else {
            try {
                yearValue = Integer.parseInt(year);
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Định dạng năm không hợp lệ. Sử dụng 'current', 'lastYear' hoặc một năm hợp lệ.");
            }
        }
        List<TopSellServiceDTO> topSellService = revenueService.getTopSellService(yearValue);
        return ResponseEntity.ok(topSellService);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Double>> getRevenueSearch(
            @RequestParam(value = "dateFrom", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam(value = "dateTo", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        // Cung cấp giá trị mặc định
        if (dateFrom == null) {
            dateFrom = LocalDate.now().withDayOfYear(1); // Ngày đầu năm hiện tại
        }
        if (dateTo == null) {
            dateTo = LocalDate.now(); // Ngày hiện tại
        }
        Map<String, Double> revenueSearch = new HashMap<>();
        revenueSearch.put("thisYear", revenueService.getTotalRevenue(dateFrom, dateTo));
        revenueSearch.put("lastYear", revenueService.getTotalRevenueLastYear(dateFrom, dateTo));
        return ResponseEntity.ok(revenueSearch);
    }
}


