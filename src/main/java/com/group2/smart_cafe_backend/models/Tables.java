package com.group2.smart_cafe_backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Tables")
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tableId;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false)
    private boolean isOn = true;

    @Column(length = 50)
    private String state = "good";
}
