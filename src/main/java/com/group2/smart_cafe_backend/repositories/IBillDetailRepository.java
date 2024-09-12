package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.dtos.MonthRevenueDTO;
import com.group2.smart_cafe_backend.dtos.ServiceRevenueDTO;
import com.group2.smart_cafe_backend.dtos.TopSellServiceDTO;
import com.group2.smart_cafe_backend.models.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IBillDetailRepository extends JpaRepository<BillDetail, Integer> {
    // doanh thu ngày
    @Query("SELECT COALESCE(SUM(bd.quantity * s.price), 0) FROM BillDetail bd " +
            "JOIN bd.service s " +
            "JOIN bd.bill b " +
            "WHERE DATE(b.dateCreated) = :date")
    double getRevenueForDate(@Param("date") LocalDate date);

    // doanh thu tháng
    @Query("SELECT COALESCE(SUM(bd.quantity * s.price), 0) FROM BillDetail bd " +
            "JOIN bd.service s " +
            "JOIN bd.bill b " +
            "WHERE MONTH(b.dateCreated) = :month AND YEAR(b.dateCreated) = :year")
    double getRevenueForMonth(@Param("month") int month, @Param("year") int year);

    // doanh thu năm
    @Query("SELECT COALESCE(SUM(bd.quantity * s.price), 0) FROM BillDetail bd " +
            "JOIN bd.service s " +
            "JOIN bd.bill b " +
            "WHERE YEAR(b.dateCreated) = :year")
    double getRevenueForYear(@Param("year") int year);

    //tổng doanh thu năm nay(theo từng tháng)
    @Query(nativeQuery = true, value = "SELECT\n" +
            "    MONTH(b.date_created) AS month,\n" +
            "    SUM(s.price * bd.quantity) AS total_revenue\n" +
            "FROM\n" +
            "    Bill_Details bd\n" +
            "        JOIN\n" +
            "    Bills b ON bd.bill_id = b.bill_id\n" +
            "        JOIN\n" +
            "    Services s ON bd.service_id = s.service_id\n" +
            "WHERE\n" +
            "    YEAR(b.date_created) = YEAR(CURDATE())  -- Lọc theo năm hiện tại\n" +
            "GROUP BY\n" +
            "    MONTH(b.date_created)\n" +
            "ORDER BY\n" +
            "    month")
    List<MonthRevenueDTO> getRevenueForMonthInCurrentYear();

    //tổng doanh thu năm ngoái(theo từng tháng)
    @Query(nativeQuery = true, value = "SELECT\n" +
            "    MONTH(b.date_created) AS month,\n" +
            "    SUM(s.price * bd.quantity) AS total_revenue\n" +
            "FROM\n" +
            "    Bill_Details bd\n" +
            "        JOIN\n" +
            "    Bills b ON bd.bill_id = b.bill_id\n" +
            "        JOIN\n" +
            "    Services s ON bd.service_id = s.service_id\n" +
            "WHERE\n" +
            "    YEAR(b.date_created) = YEAR(CURDATE()) - 1  -- Lọc theo năm trước\n" +
            "GROUP BY\n" +
            "    MONTH(b.date_created)\n" +
            "ORDER BY\n" +
            "    month")
    List<MonthRevenueDTO> getRevenueForMonthInLastYear();

    // doanh thu theo loại dịch vụ năm nay
    @Query(nativeQuery = true, value = "SELECT\n" +
            "    st.type_name AS ServiceName,\n" +
            "    SUM(sd.quantity * s.price) AS TotalRevenue\n" +
            "FROM\n" +
            "    Bills b\n" +
            "        JOIN\n" +
            "    Bill_Details sd ON b.bill_id = sd.bill_id\n" +
            "        JOIN\n" +
            "    Services s ON sd.service_id = s.service_id\n" +
            "        JOIN\n" +
            "    Service_Types st ON s.type_id = st.type_id\n" +
            "WHERE\n" +
            "    YEAR(b.date_created) = YEAR(CURDATE())\n" +
            "  AND b.status = 'completed'\n" +
            "GROUP BY\n" +
            "    st.type_name;")
    List<ServiceRevenueDTO> getRevenueServiceInCurrentYear();

    //doanh thu theo loại dịch vụ năm trước
    @Query(nativeQuery = true, value = "SELECT\n" +
            "    st.type_name AS ServiceName,\n" +
            "    SUM(sd.quantity * s.price) AS TotalRevenue\n" +
            "FROM\n" +
            "    Bills b\n" +
            "        JOIN\n" +
            "    Bill_Details sd ON b.bill_id = sd.bill_id\n" +
            "        JOIN\n" +
            "    Services s ON sd.service_id = s.service_id\n" +
            "        JOIN\n" +
            "    Service_Types st ON s.type_id = st.type_id\n" +
            "WHERE\n" +
            "    YEAR(b.date_created) = YEAR(CURDATE()) - 1\n" +
            "  AND b.status = 'completed'\n" +
            "GROUP BY\n" +
            "    st.type_name;")
    List<ServiceRevenueDTO> getRevenueServiceInLastYear();

    @Query(nativeQuery = true, value = "SELECT\n" +
            "    st.type_name AS type_name,\n" +
            "    s.service_name AS service_name,\n" +
            "    s.service_code AS service_code,\n" +
            "    COALESCE(SUM(bd.quantity), 0) AS total_quantity,\n" +
            "    s.price AS price,\n" +
            "    COALESCE(SUM(bd.quantity * s.price), 0) AS total_amount\n" +
            "FROM\n" +
            "    Services s\n" +
            "        JOIN\n" +
            "    Service_Types st ON s.type_id = st.type_id\n" +
            "        LEFT JOIN\n" +
            "    Bill_Details bd ON s.service_id = bd.service_id\n" +
            "        JOIN\n" +
            "    Bills b ON bd.bill_id = b.bill_id\n" +
            "WHERE\n" +
            "    YEAR(b.date_created) = :year -- Lọc theo năm hiện tại\n" +
            "  AND bd.quantity > 0\n" +
            "GROUP BY\n" +
            "    st.type_name,\n" +
            "    s.service_name,\n" +
            "    s.service_code,\n" +
            "    s.price\n" +
            "ORDER BY\n" +
            "    total_quantity DESC\n" +
            "LIMIT 10")
    List<TopSellServiceDTO> getTopSellService(@Param("year") int year);

    @Query("SELECT COALESCE(SUM(bd.quantity * s.price), 0) FROM BillDetail bd " +
            "JOIN bd.service s " +
            "JOIN bd.bill b " +
            "WHERE DATE(b.dateCreated) BETWEEN :dateFrom AND :dateTo")
    Double getTotalRevenue(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);


}

