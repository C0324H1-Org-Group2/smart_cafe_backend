package com.group2.smart_cafe_backend.dtos;

public interface TopSellServiceDTO {
    String getType_name();
    String getService_name();
    String getService_code();
    Integer getTotal_quantity();
    Double getPrice();
    Double getTotal_amount();
}
