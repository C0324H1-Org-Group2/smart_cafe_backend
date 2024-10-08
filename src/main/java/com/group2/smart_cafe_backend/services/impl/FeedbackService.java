package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.repositories.IFeedbackRepository;
import com.group2.smart_cafe_backend.repositories.IUserRepository;
import com.group2.smart_cafe_backend.services.IFeedbackService;
import com.group2.smart_cafe_backend.services.IFirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class FeedbackService implements IFeedbackService {

    @Autowired
    private IFeedbackRepository feedbackRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IFirebaseStorageService firebaseStorageService;

    @Override
    public Feedback saveFeedback(String email, String message, MultipartFile imageUrl) {
        Feedback newFeedback = new Feedback();

        // Lấy người tạo ngẫu nhiên từ bảng User
        newFeedback.setCreator(getRandomUser());

        newFeedback.setEmail(email);
        newFeedback.setFeedbackDate(LocalDate.now());

        newFeedback.setContent(message);

        String fileUrl = null;
        try {
            fileUrl = firebaseStorageService.uploadFile(imageUrl);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error uploading file", e
            );
        }

        newFeedback.setImageFile(fileUrl);

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
        // Lấy mã phản hồi có giá trị lớn nhất từ bảng
        String maxCode = feedbackRepository.findMaxFeedbackCode();

        if (maxCode != null) {
            // Tách phần số từ mã phản hồi (bỏ đi ký tự "FB")
            int maxNumber = Integer.parseInt(maxCode.substring(2));

            int nextNumber = maxNumber + 1;
            return "FB" + String.format("%03d", nextNumber);
        } else {
            return "FB001";
        }
    }

}
