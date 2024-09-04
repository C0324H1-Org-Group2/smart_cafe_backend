package com.group2.smart_cafe_backend.models;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Bill_Details")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billDetailId;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    private int quantity;

}

