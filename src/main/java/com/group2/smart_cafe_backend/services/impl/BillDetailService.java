package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.BillDetail;
import com.group2.smart_cafe_backend.repositories.IBillDetailRepository;
import com.group2.smart_cafe_backend.services.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailService implements IBillDetailService {

    @Autowired
    private IBillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> saveBillDetails(List<BillDetail> orderDetails) {
        return billDetailRepository.saveAll(orderDetails);
    }
}
