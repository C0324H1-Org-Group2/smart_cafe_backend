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
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

            Bucket bucket = StorageClient.getInstance().bucket();

            Blob blob = bucket.create(fileName, file.getBytes(), file.getContentType());

            blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

            return String.format("https://storage.googleapis.com/%s/%s", bucket.getName(), fileName);
        } catch (Exception e) {
            throw new IOException("Lỗi upload file ", e);
        }
    }
}
