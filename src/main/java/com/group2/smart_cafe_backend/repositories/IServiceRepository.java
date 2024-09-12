package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IServiceRepository extends JpaRepository<Service, Long> {
    @Query(value = "SELECT * FROM Services ORDER BY service_id DESC LIMIT 5", nativeQuery = true)
    List<Service> findTop5NewestServices();
    @Override
    List<Service> findAll();

    @Override
    <S extends Service> S save(S entity);

    @Query("SELECT bd.service FROM BillDetail bd WHERE bd.service.isDelete = false " +
            "GROUP BY bd.service.serviceId " +
            "ORDER BY SUM(bd.quantity) DESC LIMIT 5")
    List<Service> findTop5MostOrderedServices();

    @Query("SELECT s FROM Service s WHERE s.type.typeId = :typeId")
    List<Service> findByTypeId(@Param("typeId") Long typeId);

    Optional<Service> findById(Long id);
}

