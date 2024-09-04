package com.group2.smart_cafe_backend.models;
import com.group2.smart_cafe_backend.models.emum.BillStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Tables table;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    private LocalDateTime dateCreated;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('pending', 'completed', 'canceled', 'on_processing')")
    private BillStatus status;

}

