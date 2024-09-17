package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.Feedback;
import org.springframework.web.multipart.MultipartFile;

public interface IFeedbackService {

    Feedback saveFeedback(String email, String message, MultipartFile imageUrl);
}
