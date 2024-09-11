package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.PasswordResetToken;
import com.group2.smart_cafe_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
}

