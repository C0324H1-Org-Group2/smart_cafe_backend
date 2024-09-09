package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.Employee;


public interface IEmployeeService {
    Employee getEmployeeById(Long employeeId);
}
