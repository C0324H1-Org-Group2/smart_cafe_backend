package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.dtos.MonthRevenueDTO;
import com.group2.smart_cafe_backend.dtos.ServiceRevenueDTO;
import com.group2.smart_cafe_backend.dtos.TopSellServiceDTO;
import com.group2.smart_cafe_backend.repositories.IBillDetailRepository;
import com.group2.smart_cafe_backend.services.IRevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public List<MonthRevenueDTO> getMonthlyRevenueInCurrentYear() {
        return billDetailRepository.getRevenueForMonthInCurrentYear();
    }

    @Override
    public List<MonthRevenueDTO> getMonthlyRevenueInLastYear() {
        return billDetailRepository.getRevenueForMonthInLastYear();
    }

    @Override
    public List<ServiceRevenueDTO> getRevenueServiceInCurrentYear() {
        return billDetailRepository.getRevenueServiceInCurrentYear();
    }

    @Override
    public List<ServiceRevenueDTO> getRevenueServiceInLastYear() {
        return billDetailRepository.getRevenueServiceInLastYear();
    }

    @Override
    public Double calculateYesterdayRevenue() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return billDetailRepository.getRevenueForDate(yesterday);
    }

    @Override
    public Double calculateLastMonthRevenue() {
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        int year = lastMonth.getYear();
        int month = lastMonth.getMonthValue();
        return billDetailRepository.getRevenueForMonth(month, year);
    }

    @Override
    public Double calculateLastYearRevenue() {
        int lastYear = LocalDate.now().minusYears(1).getYear();
        return billDetailRepository.getRevenueForYear(lastYear);
    }

    @Override
    public List<TopSellServiceDTO> getTopSellService(int year) {
        return billDetailRepository.getTopSellService(year);
    }

    @Override
    public Double getTotalRevenue(LocalDate dateFrom, LocalDate dateTo) {
        return billDetailRepository.getTotalRevenue(dateFrom, dateTo);
    }

    @Override
    public Double getTotalRevenueLastYear(LocalDate dateFrom, LocalDate dateTo) {
        // Trừ đi 1 năm cho cả dateFrom và dateTo
        LocalDate lastYearStartDate = dateFrom.minusYears(1);
        LocalDate lastYearEndDate = dateTo.minusYears(1);
        return billDetailRepository.getTotalRevenue(lastYearStartDate, lastYearEndDate);
    }
}
