package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.repositories.IServiceRepository;
import com.group2.smart_cafe_backend.services.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceService implements IServiceService {
    @Autowired
    private IServiceRepository serviceRepository;


    @Override
    public List<com.group2.smart_cafe_backend.models.Service> getTop5NewestServices() {
        return serviceRepository.findTop5NewestServices();
    }
}
