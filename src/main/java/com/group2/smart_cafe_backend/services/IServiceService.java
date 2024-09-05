package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.Service;
import com.group2.smart_cafe_backend.models.ServiceType;

import java.util.List;

public interface IServiceService {
    List<Service> getTop5NewestServices();

    List<ServiceType> getAllServiceTypes();

    List<Service> getAllServices();
}
