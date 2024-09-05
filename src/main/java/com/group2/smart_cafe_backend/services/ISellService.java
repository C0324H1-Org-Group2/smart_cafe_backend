package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.Tables;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ISellService {
    List<Tables> findAllTable();

    List<Feedback> findAllFeedback();

    Feedback findFeedbackByDate(LocalDate date);

    Optional<Bill> findBillByTableId(Long tableId);
}
