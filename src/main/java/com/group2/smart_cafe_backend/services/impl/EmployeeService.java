package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.Employee;
import com.group2.smart_cafe_backend.repositories.IEmployeeRepository;
import com.group2.smart_cafe_backend.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> news = employeeRepository.findById(employeeId);
        return news.orElse(null);
    }
}
