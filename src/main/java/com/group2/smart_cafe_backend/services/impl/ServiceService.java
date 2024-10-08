package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.dtos.ServiceDto;

import com.group2.smart_cafe_backend.models.ServiceType;
import com.group2.smart_cafe_backend.models.emum.ServiceIsDelete;
import com.group2.smart_cafe_backend.repositories.IBillDetailRepository;
import com.group2.smart_cafe_backend.repositories.IServiceRepository;
import com.group2.smart_cafe_backend.repositories.IServiceTypeRepository;
import com.group2.smart_cafe_backend.services.IServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ServiceService implements IServiceService {
    @Autowired
    private IServiceRepository serviceRepository;

    @Autowired
    private IServiceTypeRepository serviceTypeRepository;

    @Autowired
    private IBillDetailRepository billDetailRepository;


    @Override
    public List<com.group2.smart_cafe_backend.models.Service> getTop5NewestServices() {
        return serviceRepository.findTop5NewestServices();
    }

    @Override
    public List<com.group2.smart_cafe_backend.models.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public List<com.group2.smart_cafe_backend.models.Service> getServicesByType(Long typeId) {
        return serviceRepository.findByTypeId(typeId);
    }


//    @Override
//    public com.group2.smart_cafe_backend.models.Service updateService(Long serviceId, @Valid ServiceDto serviceDto) {
//        com.group2.smart_cafe_backend.models.Service existingService = serviceRepository.findById(serviceId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid service ID"));
//
//        ServiceType serviceType = serviceTypeRepository.findById(serviceDto.getTypeId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid service type ID"));
//
//        existingService.setServiceCode(serviceDto.getServiceCode());
//        existingService.setServiceName(serviceDto.getServiceName());
//        existingService.setType(serviceType);
//        existingService.setPrice(serviceDto.getPrice());
//        existingService.setDescription(serviceDto.getDescription());
//        existingService.setImageUrl(serviceDto.getImageUrl());
//        existingService.setWaitTime(serviceDto.getWaitTime());
//        existingService.setStatus(serviceDto.getStatus());
//
//        return serviceRepository.save(existingService);
//    }
@Override
public com.group2.smart_cafe_backend.models.Service updateService(com.group2.smart_cafe_backend.models.Service service) {
    return serviceRepository.save(service);
}

    @Override
    public List<com.group2.smart_cafe_backend.models.Service> findAllByOrderByServiceIdDesc() {
        return serviceRepository.findAllByOrderByServiceIdDesc();

    }

    @Override
    public List<com.group2.smart_cafe_backend.models.Service> getAllServicesSortedByIdDescAndNotDeleted() {
        return serviceRepository.findAllByOrderByServiceIdDescAndIsDeleteFalse();

    }

    @Override
    @Transactional
    public com.group2.smart_cafe_backend.models.Service restoreService(Long serviceId) {
        com.group2.smart_cafe_backend.models.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Dịch vụ không tìm thấy"));

        if (service.getIsDelete() == ServiceIsDelete.DELETED) {
            service.setIsDelete(ServiceIsDelete.ACTIVE);
            return serviceRepository.save(service);
        } else {
            throw new RuntimeException("Dịch vụ không ở trạng thái đã xóa");
        }
    }

    @Override
    public com.group2.smart_cafe_backend.models.Service getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found with id " + id));
    }

    public String generateServiceCode() {
        Long count = serviceRepository.count();
        String code = String.format("CF-%04d", count + 1);
        return code;
    }

    @Override
    public com.group2.smart_cafe_backend.models.Service createService(com.group2.smart_cafe_backend.models.Service service) {
        service.setServiceCode(generateServiceCode());
        return serviceRepository.save(service);
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
    @Transactional
    public void deleteService(Long serviceId) {
        serviceRepository.logicalDeleteService(serviceId);

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
