package com.group2.smart_cafe_backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Tables")
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "is_on", nullable = false)
    private boolean isOn = true;

    @Column(name = "state", length = 50)
    private String state = "good";

    @Column(name = "is_delete", nullable = false)
    private boolean isDelete = false;
}
