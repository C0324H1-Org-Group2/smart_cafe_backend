package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.dtos.ServiceDto;
import com.group2.smart_cafe_backend.models.Service;
import com.group2.smart_cafe_backend.services.IServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private IServiceService serviceService;

    @GetMapping("/top5-newest")
    public ResponseEntity<List<Service>> getTop5NewestServices() {
        List<Service> top5Services = serviceService.getTop5NewestServices();
//        return ResponseEntity.ok(top5Services);
        return new ResponseEntity<>(top5Services, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Service>> getAllServices() {
        List<Service> services = serviceService.getAllServices();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Service> addService(@RequestBody @Valid ServiceDto serviceDto) {
        Service newService = serviceService.addService(serviceDto);
        return new ResponseEntity<>(newService, HttpStatus.CREATED);
    }
}
