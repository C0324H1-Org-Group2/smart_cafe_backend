package com.group2.smart_cafe_backend.services.impl;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.StorageClient;
import com.group2.smart_cafe_backend.services.IFirebaseStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FirebaseStorageService implements IFirebaseStorageService {
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        try {
            // Thư mục lưu trữ trong static để Spring Boot có thể truy cập trực tiếp qua URL
            String uploadDir = "src/main/resources/static/uploads/";
            java.io.File directory = new java.io.File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            java.nio.file.Path filePath = java.nio.file.Paths.get(uploadDir + fileName);
            
            // Lưu file vào ổ đĩa
            java.nio.file.Files.copy(file.getInputStream(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            // Trả về đường dẫn tương đối để Frontend gọi
            return "http://localhost:8080/uploads/" + fileName;
        } catch (Exception e) {
            throw new IOException("Lỗi lưu file local: " + e.getMessage(), e);
        }
    }
}
