package com.group2.smart_cafe_backend.models;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "image_url")
    private String imageUrl;

}

