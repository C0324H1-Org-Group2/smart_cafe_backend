package com.group2.smart_cafe_backend.services;


import com.group2.smart_cafe_backend.models.Employee;
import com.group2.smart_cafe_backend.models.User;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;

public interface IUserService {
    UserDetails loadUserByUsername(String username);
    User findByUsername(String username);
    List<Employee> findEmployeeByEmail(String email);
    User    findByEmployee(Employee employee);
    

    void updatePassword(User user, String newPassword);

    User findByUser(Employee employee);

    List<User> findAll();

    User findById(Long userId);

    User save(User user);
    void updatePasswordDateAndNotify(Long userId, LocalDate newPasswordExpiryDate) throws MessagingException;
}
