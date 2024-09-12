package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.dtos.EmployeeDTO;
import com.group2.smart_cafe_backend.dtos.UserDTO;


public interface IEmployeeService {
    EmployeeDTO getEmployeeById(Long employeeId);

    void registerEmployee(EmployeeDTO employeeDTO, UserDTO userDTO);

    void updateEmployee(Long employeeId, EmployeeDTO employeeDTO);


    void deleteEmployee(Long employeeId);
}
