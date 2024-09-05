package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.Service;
import com.group2.smart_cafe_backend.models.ServiceType;
import com.group2.smart_cafe_backend.services.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/list-service-types")
    public ResponseEntity<List<ServiceType>> getAllServiceTypes() {
        List<ServiceType> allServiceType = serviceService.getAllServiceTypes();
        return new ResponseEntity<>(allServiceType, HttpStatus.OK);
    }

    @GetMapping("/all-services")
    public ResponseEntity<List<Service>> getAllServices() {
        List<Service> allServices = serviceService.getAllServices();
        return new ResponseEntity<>(allServices, HttpStatus.OK);
    }
}
