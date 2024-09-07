package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.repositories.INewsUserRepository;
import com.group2.smart_cafe_backend.services.INewsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsUserService implements INewsUserService {
    @Autowired
    private INewsUserRepository newsUserRepository;

    @Override
    public User getUserById(Long userId) {
        return newsUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
