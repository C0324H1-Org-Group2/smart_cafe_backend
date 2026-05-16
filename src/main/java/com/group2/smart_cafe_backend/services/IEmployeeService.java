package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee getEmployeeById(Long employeeId);
    List<Employee> getAllEmployees();
}
