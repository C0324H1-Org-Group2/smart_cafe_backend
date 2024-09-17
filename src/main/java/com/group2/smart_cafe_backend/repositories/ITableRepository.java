package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.Tables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITableRepository extends JpaRepository<Tables, Long> {
    @Query("SELECT t FROM Tables t WHERE (:code IS NULL OR t.code LIKE %:code%) AND t.isDelete = false AND (:on IS NULL OR t.isOn = :on)")
    Page<Tables> findAllTablesByCodeAndOn(@Param("code") String code, @Param("on") Boolean on, Pageable pageable);
    Page<Tables> findByStateContainingIgnoreCase(String state, Pageable pageable);
@Query("SELECT t FROM Tables t WHERE (:code IS NULL OR t.code LIKE %:code%) AND (:on IS NULL OR t.isOn = :on)")
Page<Tables> findAllTablesByCodeAndOnIncludingDeleted(@Param("code") String code, @Param("on") Boolean on, Pageable pageable);
    Tables findByTableId(Long tableId);
}