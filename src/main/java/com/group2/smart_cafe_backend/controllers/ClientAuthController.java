package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.config.jwt.JwtResponse;
import com.group2.smart_cafe_backend.models.Role;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.repositories.IRoleRepository;
import com.group2.smart_cafe_backend.repositories.IUserRepository;
import com.group2.smart_cafe_backend.services.IUserService;
import com.group2.smart_cafe_backend.services.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/client/auth")
public class ClientAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Client login – reuses the existing user table.
     * Returns JWT + roles so the frontend can store them.
     */
    @PostMapping("/login")
    public ResponseEntity<?> clientLogin(@RequestBody Map<String, String> body) {
        try {
            String username = body.get("username");
            String password = body.get("password");

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtService.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userService.findByUsername(username);

            // Employee name is optional for client users
            String fullName = (currentUser.getEmployee() != null)
                    ? currentUser.getEmployee().getFullName()
                    : currentUser.getUsername();

            return ResponseEntity.ok(new JwtResponse(
                    currentUser.getUserId(), jwt,
                    userDetails.getUsername(), userDetails.getUsername(),
                    userDetails.getAuthorities(), fullName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Tên đăng nhập hoặc mật khẩu không đúng");
        }
    }

    /**
     * Client registration – creates a new user with ROLE_CLIENT.
     * No employee association required.
     */
    @PostMapping("/register")
    public ResponseEntity<?> clientRegister(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        String fullName = body.getOrDefault("fullName", username);

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return ResponseEntity.badRequest().body("Tên đăng nhập và mật khẩu không được để trống");
        }

        if (userRepository.findByUsername(username) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Tên đăng nhập đã tồn tại");
        }

        if (password.length() < 6) {
            return ResponseEntity.badRequest().body("Mật khẩu phải có ít nhất 6 ký tự");
        }

        // Find or create ROLE_CLIENT
        Role clientRole = roleRepository.findByRoleName("ROLE_CLIENT");
        if (clientRole == null) {
            clientRole = new Role();
            clientRole.setRoleName("ROLE_CLIENT");
            clientRole.setDescription("Khách hàng online");
            roleRepository.save(clientRole);
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setVerified(true);
        newUser.setEmployee(null);
        Set<Role> roles = new HashSet<>();
        roles.add(clientRole);
        newUser.setRoles(roles);

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Đăng ký thành công! Vui lòng đăng nhập.");
    }
}
