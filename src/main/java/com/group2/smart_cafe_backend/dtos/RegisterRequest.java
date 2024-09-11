package com.group2.smart_cafe_backend.dtos;

import lombok.Data;

@Data
public class RegisterRequest {
    private EmployeeDTO employeeDTO;
    private UserDTO userDTO;
}
