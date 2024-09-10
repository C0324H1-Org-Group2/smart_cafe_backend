package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

//public interface IBillDetailRepository extends JpaRepository<BillDetail, Long> {
public interface IBillDetailRepository extends JpaRepository<BillDetail, Integer> {

    @Query("SELECT COALESCE(SUM(bd.quantity * s.price), 0) FROM BillDetail bd " +
            "JOIN bd.service s " +
            "JOIN bd.bill b " +
            "WHERE DATE(b.dateCreated) = :date")
    double getRevenueForDate(@Param("date") LocalDate date);

    @Query("SELECT COALESCE(SUM(bd.quantity * s.price), 0) FROM BillDetail bd " +
            "JOIN bd.service s " +
            "JOIN bd.bill b " +
            "WHERE MONTH(b.dateCreated) = :month AND YEAR(b.dateCreated) = :year")
    double getRevenueForMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT COALESCE(SUM(bd.quantity * s.price), 0) FROM BillDetail bd " +
            "JOIN bd.service s " +
            "JOIN bd.bill b " +
            "WHERE YEAR(b.dateCreated) = :year")
    double getRevenueForYear(@Param("year") int year);
}
