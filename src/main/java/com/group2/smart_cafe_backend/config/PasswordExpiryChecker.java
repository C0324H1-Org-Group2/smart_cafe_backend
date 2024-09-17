package com.group2.smart_cafe_backend.config;

import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.services.IUserService;
import com.group2.smart_cafe_backend.services.impl.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@EnableScheduling
@Component
public class PasswordExpiryChecker {

    @Autowired
    private IUserService userService;

    @Autowired
    private EmailService emailService;

    private static final int EXPIRATION_DAYS = 30;

    public void checkPasswordExpiration() throws MessagingException {
        LocalDate today = LocalDate.now();
        List<User> users = userService.findAll();
        for (User user : users) {
            if (user.getPasswordExpiryDate() != null &&
                    user.getPasswordExpiryDate().plusDays(EXPIRATION_DAYS).isBefore(today)) {
                String resetUrl = "http://localhost:3000/admin/reset-password?token=" + UUID.randomUUID().toString();
                emailService.sendResetPasswordEmail(user.getEmployee().getEmail(), resetUrl);
            }
        }
    }
}
