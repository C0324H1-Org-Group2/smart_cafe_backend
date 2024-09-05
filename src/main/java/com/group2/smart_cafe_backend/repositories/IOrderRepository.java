package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.dtos.OrderDTO;
import com.group2.smart_cafe_backend.dtos.OrderDetailDTO;
import com.group2.smart_cafe_backend.models.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Bill, Long> {
    @Query(nativeQuery = true, value = "SELECT bills.code AS billCode, " +
            "bills.date_created AS dateCreated, " +
            "employees.full_name AS nameCreated, " +
            "tables.code AS tableCode, " +
            "SUM(bill_details.quantity * services.price) AS totalAmount " +
            "FROM bills " +
            "JOIN users ON bills.creator_id = users.user_id " +
            "JOIN employees ON users.user_id = employees.employee_id " +
            "JOIN tables ON bills.table_id = tables.table_id " +
            "JOIN bill_details ON bills.bill_id = bill_details.bill_id " +
            "JOIN services ON bill_details.service_id = services.service_id " +
            "WHERE bills.status = 'pending' " +
            "AND (:codeSearch IS NULL OR bills.code LIKE :codeSearch) " +
            "AND (:dateCreate IS NULL OR DATE(bills.date_created) = :dateCreate) " +
            "GROUP BY bills.code, bills.date_created, employees.full_name, tables.code")
    Page<OrderDTO> findAllOrders(@Param("codeSearch") String codeSearch,
                                 @Param("dateCreate") LocalDate dateCreate, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT " +
            "services.service_name AS serviceName, " +
            "bill_details.quantity AS quantity, " +
            "services.price AS price, " +
            "(bill_details.quantity * services.price) AS totalPrice " +
            "FROM Bills " +
            "JOIN Users ON bills.creator_id = users.user_id " +
            "JOIN Employees ON users.employee_id = employees.employee_id " +
            "JOIN Bill_Details ON bills.bill_id = bill_details.bill_id " +
            "JOIN Services ON bill_details.service_id = services.service_id " +
            "WHERE bills.bill_id = :billId")
    List<OrderDetailDTO> findOrderDetailsByBillId(@Param("billId") Integer billId);

}
