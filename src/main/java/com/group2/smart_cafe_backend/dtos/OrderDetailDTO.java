package com.group2.smart_cafe_backend.dtos;
import java.time.LocalDateTime;

public interface OrderDetailDTO {
    String getServiceName();
    int getQuantity();
    Double getPrice();
    Double getTotalPrice();
}
