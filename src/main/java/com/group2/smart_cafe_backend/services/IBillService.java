package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.Tables;

public interface IBillService {

    Bill createBill(Tables table);
}
