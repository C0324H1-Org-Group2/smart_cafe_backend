package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.ServiceType;
import com.group2.smart_cafe_backend.repositories.IServiceRepository;
import com.group2.smart_cafe_backend.repositories.IServiceTypeRepository;
import com.group2.smart_cafe_backend.services.IServiceService;
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
    public List<com.group2.smart_cafe_backend.models.Service> getTop5MostOrderedServices() {
        return serviceRepository.findTop5MostOrderedServices();
    }

    @Override
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }

    @Override
    public List<com.group2.smart_cafe_backend.models.Service> getAllServices() {
        return serviceRepository.findAll();
    }
}
