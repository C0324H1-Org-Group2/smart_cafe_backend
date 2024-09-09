package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.config.jwt.JwtResponse;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.services.IUserService;
import com.group2.smart_cafe_backend.services.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUserService userService;
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
}
