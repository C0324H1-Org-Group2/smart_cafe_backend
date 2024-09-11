package com.group2.smart_cafe_backend.dtos;

import com.group2.smart_cafe_backend.models.emum.Gender;
import lombok.Data;
import java.util.Date;

@Data
public class EmployeeDTO {
    private String fullName;
    private String email;
    private String address;
    private String tel;
    private Date birthday;
    private Gender gender;
    private String note;
    private String imageUrl;
}
