package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.repositories.ITableRepository;
import com.group2.smart_cafe_backend.services.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TableService implements ITableService {

    @Autowired
    private ITableRepository tableRepository;


    @Override
    public Optional<Tables> findById(Long id) {
        return tableRepository.findById(id);
    }

    @Override
    public Page<Tables> getAllTables(String code, Pageable pageable) {
        if (code == null || code.trim().isEmpty()) {
            return tableRepository.findByIsDeleteFalse(pageable); // Lấy toàn bộ nếu không tìm kiếm
        } else {
            return tableRepository.findByCodeContainingAndIsDeleteFalse(code, pageable); // Tìm kiếm nếu có mã bàn
        }
    }
    @Override
    public Optional<Tables> getTableById(Long id) {
        return tableRepository.findById(id).filter(table -> !table.isDelete());
    }

    @Override
    public Tables createTable(Tables table) {
        // Kiểm tra nếu ID đã tồn tại thì báo lỗi
        if (table.getTableId() != null && tableRepository.existsById(table.getTableId())) {
            throw new IllegalArgumentException("Table with ID " + table.getTableId() + " already exists.");
        }
        return tableRepository.save(table);
    }

    @Override
    public Tables updateTable(Long id, Tables table) {
        // Kiểm tra nếu ID không tồn tại thì báo lỗi
        if (!tableRepository.existsById(id)) {
            throw new IllegalArgumentException("Table with ID " + id + " does not exist.");
        }
        table.setTableId(id);
        return tableRepository.save(table);
    }

    @Override
    public void softDeleteTable(Long id) {
        Optional<Tables> tableOptional = tableRepository.findById(id);
        tableOptional.ifPresent(table -> {
            table.setDelete(true);  // Đánh dấu xóa mềm
            tableRepository.save(table);
        });
    }

    @Override
    public void hardDeleteTable(Long id) {
        tableRepository.deleteById(id);  // Xóa cứng
    }
    @Override
    public Tables updateTableStatus(Long id) {
        Tables table = tableRepository.findById(id).orElseThrow(() -> new RuntimeException("Table not found"));
        table.setOn(false);
        table.setBill(true);
        return tableRepository.save(table);
    }

    @Override
    public List<Tables> getAllTablesByClient() {
        return tableRepository.findAll();
    }

    @Override
    public Tables updateTableStatus1(Long id) {
        Tables table = tableRepository.findById(id).orElseThrow(() -> new RuntimeException("Table not found"));
        table.setOn(true);
        table.setBill(false);
        return tableRepository.save(table);
    }

    @Override
    public Page<Tables> findByState(String state, Pageable pageable) {
        return tableRepository.findByStateContainingIgnoreCase(state, pageable);
    }
}
