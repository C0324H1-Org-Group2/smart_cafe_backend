package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.dtos.BillDTO;
import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.repositories.IBillRepository;
import com.group2.smart_cafe_backend.repositories.IFeedbackRepository;
import com.group2.smart_cafe_backend.repositories.ITableRepository;
import com.group2.smart_cafe_backend.services.ISellService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<Feedback> findFeedbackByDate(LocalDate date) {
        return feedbackRepository.findFeedbackByDate(date);
    }

    public List<BillDTO> findBillByTableId(Long tableId) {
        List<Object[]> results = billRepository.findBillByTableId(tableId);
        List<BillDTO> billDTOs = new ArrayList<>();

        for (Object[] result : results) {
            String serviceName = (String) result[0];
            Integer quantity = (Integer) result[1];
            BigDecimal price = (BigDecimal) result[2];
            String tableCode = (String) result[3];

            BillDTO billDTO = new BillDTO(serviceName, quantity, price, tableCode);
            billDTOs.add(billDTO);
        }

        return billDTOs;
    }

    @Override
    public List<Feedback> findAllFeedback() {
        return feedbackRepository.findAll();
    }
}
