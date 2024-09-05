package com.group2.smart_cafe_backend.services;


import com.group2.smart_cafe_backend.dtos.ServiceDto;
import com.group2.smart_cafe_backend.models.Service;

import java.util.List;

public interface IServiceService {
    List<Service> getTop5NewestServices();
    List<Service> getAllServices();
    Service addService(ServiceDto serviceDTO);
    void deleteService(Long serviceId);




}
