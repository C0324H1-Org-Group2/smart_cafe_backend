package com.group2.smart_cafe_backend.dtos;

import java.math.BigDecimal;

public class BillDTO {
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String numberTable;

    public BillDTO() {
    }

    public BillDTO(String name, Integer quantity, BigDecimal price, String numberTable) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.numberTable = numberTable;
    }

    // Getters và Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(String numberTable) {
        this.numberTable = numberTable;
    }
}
