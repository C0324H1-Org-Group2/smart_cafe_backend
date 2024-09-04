package com.group2.smart_cafe_backend.models;
import com.group2.smart_cafe_backend.models.emum.NewsStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "News")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newsId;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private LocalDate publishDate;

    @Column(length = 255)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('published', 'draft', 'archived') DEFAULT 'draft'")
    private NewsStatus status;

}

