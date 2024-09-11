package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.BillDetail;

import java.util.List;

public interface IBillDetailService {
    List<BillDetail> saveBillDetails(List<BillDetail> orderDetails);
}
