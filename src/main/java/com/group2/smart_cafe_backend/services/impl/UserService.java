package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.dtos.UserPrinciple;
import com.group2.smart_cafe_backend.models.Employee;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.repositories.IEmployeeRepository;
import com.group2.smart_cafe_backend.repositories.IUserRepository;
import com.group2.smart_cafe_backend.services.IUserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return UserPrinciple.build(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Employee> findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public User findByEmployee(Employee employee) {
        return userRepository.findByEmployee(employee);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public User findByUser(Employee employee) {
        return userRepository.findByEmployee(employee);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updatePasswordDateAndNotify(Long userId, LocalDate newPasswordExpiryDate) throws MessagingException {
        User user = findById(userId);
        if (user != null) {
            user.setPasswordExpiryDate(newPasswordExpiryDate);
            save(user);
            String token = UUID.randomUUID().toString();
            passwordResetService.createPasswordResetToken(user, token);
            String resetUrl = "http://localhost:3000/admin/reset-password?token=" + token;
            emailService.sendResetPasswordEmail30Days(user.getEmployee().getEmail(), resetUrl);
        }
    }


}
