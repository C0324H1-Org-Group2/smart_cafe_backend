package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.repositories.IBillDetailRepository;
import com.group2.smart_cafe_backend.services.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class RevenueService implements IRevenueService {

    @Autowired
    private IBillDetailRepository billDetailRepository;

    public double calculateTodayRevenue() {
        return billDetailRepository.getRevenueForDate(LocalDate.now());
    }

    public double calculateThisMonthRevenue() {
        return billDetailRepository.getRevenueForMonth(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
    }

    public double calculateThisYearRevenue() {
        return billDetailRepository.getRevenueForYear(LocalDate.now().getYear());
    }
}
