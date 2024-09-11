package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.PasswordResetToken;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.repositories.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordResetService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    public void createPasswordResetToken(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setToken(token);
        myToken.setUser(user);
        myToken.setCreatedAt(LocalDateTime.now()); // Ngày và giờ hiện tại
//        myToken.setCreatedAt(calculateExpiryDate(24 * 60)); // Thiết lập thời gian hết hạn
        tokenRepository.save(myToken);
    }

    public PasswordResetToken validatePasswordResetToken(String token) {
        PasswordResetToken passToken = tokenRepository.findByToken(token);
        if (passToken == null || passToken.getCreatedAt().isBefore(LocalDateTime.now())) {
            return null;
        }
        return passToken;
    }

    private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {
        return LocalDateTime.now().plusMinutes(expiryTimeInMinutes);
    }
}
