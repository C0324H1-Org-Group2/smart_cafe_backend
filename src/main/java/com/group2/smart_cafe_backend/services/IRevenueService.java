package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.dtos.MonthRevenueDTO;
import com.group2.smart_cafe_backend.dtos.ServiceRevenueDTO;
import com.group2.smart_cafe_backend.dtos.TopSellServiceDTO;

import java.time.LocalDate;
import java.util.List;

public interface IRevenueService {
    double calculateTodayRevenue();
    double calculateThisMonthRevenue();
    double calculateThisYearRevenue();

    List<MonthRevenueDTO> getMonthlyRevenueInCurrentYear();

    List<MonthRevenueDTO> getMonthlyRevenueInLastYear();

    List<ServiceRevenueDTO> getRevenueServiceInCurrentYear();

    List<ServiceRevenueDTO> getRevenueServiceInLastYear();

    Double calculateYesterdayRevenue();

    Double calculateLastMonthRevenue();

    Double calculateLastYearRevenue();

    List<TopSellServiceDTO> getTopSellService(int year);

    Double getTotalRevenue(LocalDate dateFrom, LocalDate dateTo);

    Double getTotalRevenueLastYear(LocalDate dateFrom, LocalDate dateTo);
}

