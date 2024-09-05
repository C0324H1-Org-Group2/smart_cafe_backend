package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.Service;

import java.util.List;

public interface IServiceService {
    List<Service> getTop5NewestServices();

    List<Service> getTop5MostOrderedServices();
}
