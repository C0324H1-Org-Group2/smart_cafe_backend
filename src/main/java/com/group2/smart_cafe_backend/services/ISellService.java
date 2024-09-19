package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.dtos.BillDTO;
import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.Tables;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ISellService {
    List<Tables> findAllTable();

    List<Feedback> findFeedbackByDate(LocalDate date);

    List<BillDTO> findBillByTableId(Long tableId);

    List<Feedback> findAllFeedback();

    boolean updateTableStatus(Long tableId);

    boolean updateBillStatus(Long tableId,Long userId);

    Tables setStatusEmployee(Long tableId);

    void setStatusOrder(Long tableId);
}
