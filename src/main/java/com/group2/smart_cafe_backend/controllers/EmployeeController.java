package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.dtos.EmployeeDTO;
import com.group2.smart_cafe_backend.dtos.RegisterRequest;
import com.group2.smart_cafe_backend.dtos.UserDTO;
import com.group2.smart_cafe_backend.models.Employee;
import com.group2.smart_cafe_backend.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;



    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(employeeId);
        if (employeeDTO != null) {
            return ResponseEntity.ok(employeeDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody RegisterRequest request) {
        try {
            employeeService.registerEmployee(request.getEmployeeDTO(), request.getUserDTO());
            return ResponseEntity.ok().body("Account successfully created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO) {
        try {
            employeeService.updateEmployee(employeeId, employeeDTO);
            return ResponseEntity.ok().body("Employee successfully updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.ok().body("Employee successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


}
