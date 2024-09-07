package com.group2.smart_cafe_backend.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFirebaseStorageService {
    String uploadFile(MultipartFile file) throws IOException;
}
