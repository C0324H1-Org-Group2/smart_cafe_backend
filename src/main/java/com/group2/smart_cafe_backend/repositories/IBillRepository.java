package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.dtos.BillDTO;
import com.group2.smart_cafe_backend.models.Bill;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBillRepository extends JpaRepository<Bill,Long> {


    @Query(nativeQuery = true, value = "SELECT s.service_name, bd.quantity, s.price, t.code,b.status, b.date_created " +
            "FROM bills b " +
            "JOIN bill_details bd ON b.bill_id = bd.bill_id " +
            "JOIN services s ON s.service_id = bd.service_id " +
            "JOIN tables t ON t.table_id = b.table_id " +
            "WHERE t.table_id = :id AND b.status = 'pending'")
    List<Object[]> findBillByTableId(@Param("id") Long tableId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE bills SET status = 'completed' WHERE table_id = :id AND status = 'pending'")
    int updateBillStatus(@Param("id") Long tableId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE tables SET is_pay = 0 , is_on = 1 WHERE table_id = :id")
    int updateTableStatus(@Param("id") Long tableId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE tables SET is_bill = 0 WHERE table_id = :id")
    void setStatusOrder(@Param("id") Long tableId);

    @Query("SELECT b.code FROM Bill b ORDER BY b.code DESC LIMIT 1")
    String findMaxBillCode();


    @Modifying
    @Transactional
    @Query("DELETE FROM Bill b WHERE b.table.tableId = :tableId")
    void deleteBillsByTableId(@Param("tableId") Long tableId);
}
