package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.dtos.ServiceDto;
import com.group2.smart_cafe_backend.models.Bill;
import com.group2.smart_cafe_backend.models.BillDetail;
import com.group2.smart_cafe_backend.models.Service;
import com.group2.smart_cafe_backend.models.ServiceType;
import com.group2.smart_cafe_backend.models.emum.ServiceStatus;
import com.group2.smart_cafe_backend.repositories.IServiceTypeRepository;
import com.group2.smart_cafe_backend.services.IFirebaseStorageService;
import com.group2.smart_cafe_backend.services.IServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private IServiceService serviceService;
    @Autowired
    private IFirebaseStorageService firebaseStorageService;
    @Autowired
    private IServiceTypeRepository serviceTypeRepository;

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

    @PostMapping
    public ResponseEntity<?> createService(@RequestParam("imageUrl") MultipartFile file,
                                           @RequestParam("serviceCode") String serviceCode,
                                           @RequestParam("serviceName") String serviceName,
                                           @RequestParam("typeId") Long typeId,
                                           @RequestParam("price") BigDecimal price,
                                           @RequestParam("description") String description,
                                           @RequestParam("waitTime") String waitTime,
                                           @RequestParam("status") String status) {
        try {
            String imageUrl = firebaseStorageService.uploadFile(file);

            ServiceType serviceType = serviceTypeRepository.findById(typeId)
                    .orElseThrow(() -> new RuntimeException("Service Type không tìm thấy"));

            Service newService = new Service();
            newService.setServiceCode(serviceCode);
            newService.setServiceName(serviceName);
            newService.setType(serviceType);
            newService.setPrice(price);
            newService.setDescription(description);
            newService.setImageUrl(imageUrl);
//            LocalTime waitTimeConverted = LocalTime.parse(waitTime);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime waitTimeConverted = LocalTime.parse(waitTime, formatter);

            newService.setWaitTime(waitTimeConverted);
            newService.setStatus(ServiceStatus.valueOf(status.toLowerCase()));

            Service createService = serviceService.createService(newService);

            return new ResponseEntity<>(createService, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi tạo món" + e.getMessage());
        }
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
    @PatchMapping("/update/{serviceId}")
    public ResponseEntity<Service> updateService(@PathVariable Long serviceId, @RequestBody @Valid ServiceDto serviceDto) {
        Service updatedService = serviceService.updateService(serviceId, serviceDto);
        return new ResponseEntity<>(updatedService, HttpStatus.OK);
    }
    @GetMapping("/detail/{serviceId}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long serviceId) {
        Service service = serviceService.getServiceById(serviceId);
        return new ResponseEntity<>(service, HttpStatus.OK);
    }

}
