package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.Tables;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ITableService {
Page<Tables> getAllTables(String code, Boolean on, boolean includeDeleted, Pageable pageable);
    Optional<Tables> getTableById(Long id);         // Lấy bàn theo ID
    Tables createTable(Tables table);               // Tạo mới bàn
    Tables updateTable(Long id, Tables table);      // Cập nhật bàn
    void softDeleteTable(Long id);                  // Xóa mềm bàn theo ID
    void hardDeleteTable(Long id);               // Xóa cứng bàn theo ID
    Tables updateTableStatus(Long id);

    Page<Tables> getTablesByIsOn(boolean isOn, Pageable pageable);

    List<Tables> getAllTablesByClient();

    Tables updateTableStatus1(Long id);

    Page<Tables> findByState(String state, Pageable pageable);

    Tables updateTableStatusBill(Long id);

    Tables getTableCurrent(Long tableId);
    boolean isTableBill(Long tableId);

    Tables callEmployee(Long id);
}
