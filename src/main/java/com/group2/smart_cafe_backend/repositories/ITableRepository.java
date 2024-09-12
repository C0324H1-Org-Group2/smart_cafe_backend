package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.Tables;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITableRepository extends JpaRepository<Tables, Long> {
    Page<Tables> findByIsDeleteFalse(Pageable pageable);  // Cập nhật để hỗ trợ phân trang

    List<Tables> findByIsOnTrue();

}
