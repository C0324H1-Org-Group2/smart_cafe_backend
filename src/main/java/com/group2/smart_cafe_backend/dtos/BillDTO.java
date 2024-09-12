package com.group2.smart_cafe_backend.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class BillDTO {
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String tableCode;
    private String status;
    private LocalDateTime date;

    public BillDTO(String serviceName, Integer quantity, BigDecimal price, String tableCode, String status, LocalDateTime date) {
        this.name = serviceName;
        this.quantity = quantity;
        this.price = price;
        this.tableCode = tableCode;
        this.status = status;
        this.date = date;
    }


}
