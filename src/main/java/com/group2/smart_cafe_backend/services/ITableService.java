package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.Tables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ITableService {
    Optional<Tables> findById(Long id);
    Page<Tables> getAllTables(Pageable pageable);
    Optional<Tables> getTableById(Long id);         // Lấy bàn theo ID
    Tables createTable(Tables table);               // Tạo mới bàn
    Tables updateTable(Long id, Tables table);      // Cập nhật bàn
    void softDeleteTable(Long id);                  // Xóa mềm bàn theo ID
    void hardDeleteTable(Long id);                  // Xóa cứng bàn theo ID

    Page<Tables> findByState(String state, Pageable pageable);
}
