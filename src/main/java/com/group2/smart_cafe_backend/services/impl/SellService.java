package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.repositories.IBillRepository;
import com.group2.smart_cafe_backend.repositories.IFeedbackRepository;
import com.group2.smart_cafe_backend.repositories.ITableRepository;
import com.group2.smart_cafe_backend.services.ISellService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class SellService implements ISellService {
    @Autowired
    private ITableRepository tableRepository;

    @Autowired
    private IFeedbackRepository feedbackRepository;

    @Autowired
    private IBillRepository billRepository;


    @Override
    public List<Tables> findAllTable() {
        return tableRepository.findAll();
    }

    @Override
    public List<Feedback> findAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback findFeedbackByDate(LocalDate date) {
        return feedbackRepository.findFeedbackByDate(date);
    }

    @Override
    public Optional<Bill> findBillByTableId(Long tableId) {
        return billRepository.findBillByTableId(tableId);
    }
}
