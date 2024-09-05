package com.group2.smart_cafe_backend.dtos;


public interface OrderDTO {
    String getBillCode();
    String getDateCreated();
    String getNameCreated();
    String getTableCode();
    Double getTotalAmount();
}
