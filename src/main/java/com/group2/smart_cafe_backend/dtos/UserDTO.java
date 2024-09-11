package com.group2.smart_cafe_backend.dtos;


import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private String username;
    private String password;
    private Long employeeId;
    private Set<Long> roleIds;
}

