package com.group2.smart_cafe_backend.models;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int positionId;

    @Column(name = "position_name", unique = true, nullable = false)
    private String positionName;

    @Column(columnDefinition = "TEXT")
    private String description;

}

