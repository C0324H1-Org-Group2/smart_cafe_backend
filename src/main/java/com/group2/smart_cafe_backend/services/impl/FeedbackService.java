package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.repositories.IFeedbackRepository;
import com.group2.smart_cafe_backend.repositories.IUserRepository;
import com.group2.smart_cafe_backend.services.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class FeedbackService implements IFeedbackService {

    @Autowired
    private IFeedbackRepository feedbackRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Feedback saveFeedback(String email, String message) {
        Feedback newFeedback = new Feedback();

        // Lấy người tạo ngẫu nhiên từ bảng User
        newFeedback.setCreator(getRandomUser());

        newFeedback.setEmail(email);
        newFeedback.setFeedbackDate(LocalDate.now());

        newFeedback.setContent(message);

        newFeedback.setCode(generateFeedbackCode());

        return feedbackRepository.save(newFeedback);
    }

    // Lấy ngẫu nhiên một User từ bảng User
    private User getRandomUser() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users available");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(users.size());
        return users.get(randomIndex);
    }

    private String generateFeedbackCode() {
        Long count = feedbackRepository.count();
        Long nextNumber = count + 1;
        return "FB" + String.format("%03d", nextNumber);
    }
}
