package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.Tables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITableRepository extends JpaRepository<Tables, Long> {
    Page<Tables> findByCodeContainingAndIsDeleteFalse(String code, Pageable pageable);
    Page<Tables> findByIsDeleteFalse(Pageable pageable); // Để lấy tất cả nếu không tìm kiếm

    Page<Tables> findByStateContainingIgnoreCase(String state, Pageable pageable);
}