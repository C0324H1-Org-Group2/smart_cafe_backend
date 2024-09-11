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

    private Random random = new Random();


    @Override
    public Page<Tables> getAllTables(Pageable pageable) {
        // Chỉ lấy các bàn chưa bị xóa (isDelete = false) với phân trang
        return tableRepository.findByIsDeleteFalse(pageable);
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
    public Tables getRandomAvailableTable() {
        // Lấy danh sách các bảng có trạng thái isOn = true
        List<Tables> availableTables = tableRepository.findByIsOnTrue();

        if (availableTables.isEmpty()) {
            throw new RuntimeException("No available tables found");
        }

        // Chọn ngẫu nhiên một bảng từ danh sách
        int index = random.nextInt(availableTables.size());
        return availableTables.get(index);
    }

    @Override
    public Tables updateTableStatus(Long id) {
        Tables table = tableRepository.findById(id).orElseThrow(() -> new RuntimeException("Table not found"));
        table.setOn(false);
        return tableRepository.save(table);
    }
}
