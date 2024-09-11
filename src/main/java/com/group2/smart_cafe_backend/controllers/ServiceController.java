package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.dtos.ServiceDto;
import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.BillDetail;
import com.group2.smart_cafe_backend.models.Service;
import com.group2.smart_cafe_backend.models.ServiceType;
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

    @PostMapping("/add")
    public ResponseEntity<Service> addService(@RequestBody @Valid ServiceDto serviceDto) {
        Service newService = serviceService.addService(serviceDto);
        return new ResponseEntity<>(newService, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/top5-most-ordered")
    public ResponseEntity<List<Service>> getTop5MostOrderedServices() {
        List<Service> top5MostOrdered = serviceService.getTop5MostOrderedServices();
        return new ResponseEntity<>(top5MostOrdered, HttpStatus.OK);
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

    @GetMapping("/services-by-type/{typeId}")
    public ResponseEntity<List<Service>> getServicesByType(@PathVariable Long typeId) {
        List<Service> servicesByType = serviceService.getServicesByType(typeId);
        return new ResponseEntity<>(servicesByType, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Service> updateService(@PathVariable Long id, @RequestBody @Valid ServiceDto serviceDto) {
        Service updatedService = serviceService.updateService(id, serviceDto);
        return new ResponseEntity<>(updatedService, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        Service service = serviceService.getServiceById(id);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

}
