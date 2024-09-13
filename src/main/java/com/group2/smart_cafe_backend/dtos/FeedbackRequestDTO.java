package com.group2.smart_cafe_backend.dtos;

public class FeedbackRequestDTO {
    private String email;
    private String message;

    // Getters và Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
