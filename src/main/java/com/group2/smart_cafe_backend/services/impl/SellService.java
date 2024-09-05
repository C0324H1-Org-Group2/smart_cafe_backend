package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.repositories.IFeedbackRepository;
import com.group2.smart_cafe_backend.repositories.ITableRepository;
import com.group2.smart_cafe_backend.services.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class SellService implements IService {
    @Autowired
    private ITableRepository tableRepository;

    @Autowired
    private IFeedbackRepository feedbackRepository;


    @Override
    public List<Tables> findAllTable() {
        return tableRepository.findAll();
    }

    @Override
    public List<Feedback> findAllFeedback() {
        return feedbackRepository.findAll();
    }
}
