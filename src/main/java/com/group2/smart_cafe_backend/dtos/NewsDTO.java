package com.group2.smart_cafe_backend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewsDTO {
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;

    @NotBlank(message = "Nội dung không được để trống")
    private String content;

//    @NotBlank(message = "Hình ảnh không được để trống")
//    private String imageUrl;
}
