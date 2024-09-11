package com.group2.smart_cafe_backend.services;


import com.group2.smart_cafe_backend.dtos.ServiceDto;
import com.group2.smart_cafe_backend.models.BillDetail;
import com.group2.smart_cafe_backend.models.Service;
import com.group2.smart_cafe_backend.models.ServiceType;

import java.util.List;

public interface IServiceService {
    List<Service> getTop5NewestServices();

    Service addService(ServiceDto serviceDTO);
    void deleteService(Long serviceId);





    List<Service> getTop5MostOrderedServices();

    List<ServiceType> getAllServiceTypes();

    List<Service> getAllServices();

    List<Service> getServicesByType(Long typeId);
    Service updateService(Long serviceId, ServiceDto serviceDto);

}
