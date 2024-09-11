package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.config.jwt.JwtResponse;
import com.group2.smart_cafe_backend.models.Employee;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.services.IUserService;
import com.group2.smart_cafe_backend.services.impl.EmailService;
import com.group2.smart_cafe_backend.services.impl.JwtService;
import com.group2.smart_cafe_backend.services.impl.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordResetService passwordResetService;
    @Autowired
    private EmailService emailService;
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody User username) {

        Authentication authentication
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username.getUsername(), username.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(username.getUsername());
        return ResponseEntity.ok(new JwtResponse(currentUser.getUserId(), jwt, userDetails.getUsername(), userDetails.getUsername(), userDetails.getAuthorities()));
    }
    @PostMapping("/api/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> emailRequest) {
        String email = emailRequest.get("email");
        List<Employee> employees = userService.findEmployeeByEmail(email);
        if (employees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email không tồn tại");
        }
//        Employee employee = employees.get(0);
        long defaultUser = 1L;
        User user = userService.findByUser(defaultUser);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy người dùng tương ứng");
        }
        String token = UUID.randomUUID().toString();
        passwordResetService.createPasswordResetToken(user, token);
        String resetUrl = "http://localhost:8080/api/reset-password?token=" + token;
        emailService.sendResetPasswordEmail(email, resetUrl);
        return ResponseEntity.ok("Vui lòng kiểm tra email để đặt lại mật khẩu");
    }
}
