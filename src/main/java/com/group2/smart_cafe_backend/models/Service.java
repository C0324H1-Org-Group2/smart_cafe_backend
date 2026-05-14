package com.group2.smart_cafe_backend.models;
import com.group2.smart_cafe_backend.models.emum.ServiceIsDelete;
import com.group2.smart_cafe_backend.models.emum.ServiceStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "Services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @Column(nullable = false, unique = true, length = 50)
    private String serviceCode;

    @Column(length = 100)
    private String serviceName;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ServiceType type;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String imageUrl;

    private LocalTime waitTime;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('available', 'unavailable', 'out_of_stock')")
    private ServiceStatus status;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ACTIVE', 'DELETED') DEFAULT 'ACTIVE'", nullable = false)
    private ServiceIsDelete isDelete = ServiceIsDelete.ACTIVE;
}

