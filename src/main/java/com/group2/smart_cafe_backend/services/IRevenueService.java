package com.group2.smart_cafe_backend.services;

public interface IRevenueService {
    double calculateTodayRevenue();
    double calculateThisMonthRevenue();
    double calculateThisYearRevenue();
}
