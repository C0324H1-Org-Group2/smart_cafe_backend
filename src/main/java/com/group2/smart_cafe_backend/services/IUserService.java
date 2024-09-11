package com.group2.smart_cafe_backend.services;


import com.group2.smart_cafe_backend.models.Employee;
import com.group2.smart_cafe_backend.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    UserDetails loadUserByUsername(String username);
    User findByUsername(String username);
    List<Employee> findEmployeeByEmail(String email);
    User    findByEmployee(Employee employee);

    User findByUser(long defaultUser);
}
