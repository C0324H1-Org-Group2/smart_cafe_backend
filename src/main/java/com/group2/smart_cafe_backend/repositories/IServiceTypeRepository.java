package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceTypeRepository extends JpaRepository<ServiceType, Long> {
}
