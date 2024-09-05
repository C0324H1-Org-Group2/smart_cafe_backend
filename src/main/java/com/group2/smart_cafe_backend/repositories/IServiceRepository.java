package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IServiceRepository extends JpaRepository<Service, Long> {
    @Query(value = "SELECT * FROM Services ORDER BY service_id DESC LIMIT 5", nativeQuery = true)
    List<Service> findTop5NewestServices();

    @Query("SELECT bd.service FROM BillDetail bd WHERE bd.service.isDelete = false " +
            "GROUP BY bd.service.serviceId " +
            "ORDER BY SUM(bd.quantity) DESC LIMIT 5")
    List<Service> findTop5MostOrderedServices();
}

