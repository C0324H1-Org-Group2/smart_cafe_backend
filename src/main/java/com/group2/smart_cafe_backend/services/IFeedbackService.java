package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.Feedback;

public interface IFeedbackService {

    Feedback saveFeedback(String email, String message);
}
