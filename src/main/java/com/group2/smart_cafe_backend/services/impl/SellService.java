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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            String status = (String) result[4];
            Timestamp dateCreated = (Timestamp) result[5]; // Lấy Timestamp
            LocalDateTime date = dateCreated.toLocalDateTime(); // Chuyển sang LocalDateTime


            BillDTO billDTO = new BillDTO(serviceName, quantity, price, tableCode,status,date);
            billDTOs.add(billDTO);
        }

        return billDTOs;
    }

    @Override
    public List<Feedback> findAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public boolean updateBillStatus(Long tableId) {
        int updatedRows = billRepository.updateBillStatus(tableId);
//        số hàng cập nhật lớn hơn 0 thì trả về true, ngược lại trả về false
        return updatedRows > 0;
    }

    @Override
    public void setStatusEmployee(Long tableId) {
        billRepository.setStatusEmployee(tableId);
    }

    @Override
    public void setStatusOrder(Long tableId) {
        billRepository.setStatusOrder(tableId);
    }

    @Override
    public boolean updateTableStatus(Long tableId) {
        int updatedRows = billRepository.updateTableStatus(tableId);
//        số hàng cập nhật lớn hơn 0 thì trả về true, ngược lại trả về false
        return updatedRows > 0;
    }

}
