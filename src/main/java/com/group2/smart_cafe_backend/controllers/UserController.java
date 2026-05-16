package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.Employee;
import com.group2.smart_cafe_backend.models.Role;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.services.IRoleService;
import com.group2.smart_cafe_backend.services.IUserService;
import com.group2.smart_cafe_backend.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IRoleService roleService;
    
    @Autowired
    private IEmployeeService employeeService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        if (userService.findByUsername(userRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setVerified(true);
        
        if (userRequest.getEmployeeId() != null) {
            Employee emp = employeeService.getEmployeeById(userRequest.getEmployeeId());
            user.setEmployee(emp);
        }
        
        Set<Role> roles = new HashSet<>();
        for (Long roleId : userRequest.getRoleIds()) {
            roleService.findAll().stream()
                .filter(r -> r.getRoleId().equals(roleId))
                .findFirst()
                .ifPresent(roles::add);
        }
        user.setRoles(roles);
        
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
        try {
            User user = userService.findById(id);
            if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            }
            if (userRequest.getEmployeeId() != null) {
                Employee emp = employeeService.getEmployeeById(userRequest.getEmployeeId());
                user.setEmployee(emp);
            }
            if (userRequest.getRoleIds() != null) {
                Set<Role> roles = new HashSet<>();
                for (Long roleId : userRequest.getRoleIds()) {
                    roleService.findAll().stream()
                        .filter(r -> r.getRoleId().equals(roleId))
                        .findFirst()
                        .ifPresent(roles::add);
                }
                user.setRoles(roles);
            }
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/lock")
    public ResponseEntity<?> toggleLockUser(@PathVariable("id") Long id) {
        try {
            User user = userService.findById(id);
            user.setVerified(!user.isVerified()); // Using isVerified as locked/unlocked flag
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    public static class UserRequest {
        private String username;
        private String password;
        private Long employeeId;
        private List<Long> roleIds;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public Long getEmployeeId() { return employeeId; }
        public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
        public List<Long> getRoleIds() { return roleIds; }
        public void setRoleIds(List<Long> roleIds) { this.roleIds = roleIds; }
    }
}
