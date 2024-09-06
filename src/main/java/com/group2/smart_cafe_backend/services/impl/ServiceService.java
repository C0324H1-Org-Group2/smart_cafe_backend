package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.dtos.ServiceDto;

import com.group2.smart_cafe_backend.models.ServiceType;
import com.group2.smart_cafe_backend.repositories.IServiceRepository;
import com.group2.smart_cafe_backend.repositories.IServiceTypeRepository;
import com.group2.smart_cafe_backend.services.IServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceService implements IServiceService {
    @Autowired
    private IServiceRepository serviceRepository;
    @Autowired
    private IServiceTypeRepository serviceTypeRepository;


    @Override
    public List<com.group2.smart_cafe_backend.models.Service> getTop5NewestServices() {
        return serviceRepository.findTop5NewestServices();
    }

    @Override
    public List<com.group2.smart_cafe_backend.models.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public com.group2.smart_cafe_backend.models.Service updateService(Long serviceId, @Valid ServiceDto serviceDto) {
        com.group2.smart_cafe_backend.models.Service existingService = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid service ID"));

        ServiceType serviceType = serviceTypeRepository.findById(serviceDto.getTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid service type ID"));

        existingService.setServiceCode(serviceDto.getServiceCode());
        existingService.setServiceName(serviceDto.getServiceName());
        existingService.setType(serviceType);
        existingService.setPrice(serviceDto.getPrice());
        existingService.setDescription(serviceDto.getDescription());
        existingService.setImageUrl(serviceDto.getImageUrl());
        existingService.setWaitTime(serviceDto.getWaitTime());
        existingService.setStatus(serviceDto.getStatus());

        return serviceRepository.save(existingService);
    }


    @Override
    public com.group2.smart_cafe_backend.models.Service addService(@Valid ServiceDto serviceDto) {
        ServiceType serviceType = serviceTypeRepository.findById(serviceDto.getTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid service type ID"));

        com.group2.smart_cafe_backend.models.Service service = new com.group2.smart_cafe_backend.models.Service();
        service.setServiceCode(serviceDto.getServiceCode());
        service.setServiceName(serviceDto.getServiceName());
        service.setType(serviceType);
        service.setPrice(serviceDto.getPrice());
        service.setDescription(serviceDto.getDescription());
        service.setImageUrl(serviceDto.getImageUrl());
        service.setWaitTime(serviceDto.getWaitTime());
        service.setStatus(serviceDto.getStatus());

        return serviceRepository.save(service);
    }

    @Override
    public void deleteService(Long serviceId) {
        serviceRepository.deleteById(serviceId);

    }



    @Override
    public List<com.group2.smart_cafe_backend.models.Service> getTop5MostOrderedServices() {
        return serviceRepository.findTop5MostOrderedServices();
    }

    @Override
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }

}
