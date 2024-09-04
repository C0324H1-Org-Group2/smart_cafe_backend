package com.group2.smart_cafe_backend.models;
import com.group2.smart_cafe_backend.models.emum.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String address;

    private String tel;

    private Date birthday;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('male', 'female', 'unknown')")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "is_verified", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isVerified;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "password_expiry_date")
    private Date passwordExpiryDate;

}

