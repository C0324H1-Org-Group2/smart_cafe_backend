package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.dtos.BillDTO;
import com.group2.smart_cafe_backend.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBillRepository extends JpaRepository<Bill,Long> {

//    @Query(nativeQuery = true, value = "SELECT s.service_name, bd.quantity, s.price, t.code " +
//            "FROM bills b " +
//            "JOIN bill_details bd ON b.bill_id = bd.bill_id " +
//            "JOIN services s ON s.service_id = bd.service_id " +
//            "JOIN tables t ON t.table_id = b.table_id " +
//            "WHERE t.table_id = :id")
//    List<BillDTO> findBillByTableId(@Param("id") Long tableId);

    @Query(nativeQuery = true, value = "SELECT s.service_name, bd.quantity, s.price, t.code " +
            "FROM bills b " +
            "JOIN bill_details bd ON b.bill_id = bd.bill_id " +
            "JOIN services s ON s.service_id = bd.service_id " +
            "JOIN tables t ON t.table_id = b.table_id " +
            "WHERE t.table_id = :id")
    List<Object[]> findBillByTableId(@Param("id") Long tableId);






}
