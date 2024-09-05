package com.group2.smart_cafe_backend.models;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @ManyToOne()
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(length = 100)
    private String email;

    @Column(name = "feedback_date")
    private LocalDate feedbackDate;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

}
