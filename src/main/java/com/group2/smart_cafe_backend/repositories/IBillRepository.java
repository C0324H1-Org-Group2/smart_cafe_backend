package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBillRepository extends JpaRepository<Bill,Long> {


    @Query(nativeQuery = true,value = "SELECT * FROM bills WHERE table_id = :id")
    Optional<Bill> findBillByTableId(@Param("id") Long tableId);
}
