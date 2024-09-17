package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.repositories.IBillDetailRepository;
import com.group2.smart_cafe_backend.repositories.IBillRepository;
import com.group2.smart_cafe_backend.repositories.ITableRepository;
import com.group2.smart_cafe_backend.services.ITableService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TableService implements ITableService {

    @Autowired
    private ITableRepository tableRepository;

    @Autowired
    private IBillDetailRepository billDetailRepository;

    @Autowired
    private IBillRepository billRepository;

@Override
public Page<Tables> getAllTables(String code, Boolean on, boolean includeDeleted, Pageable pageable) {
    if (includeDeleted) {
        return tableRepository.findAllTablesByCodeAndOnIncludingDeleted(code == null || code.isEmpty() ? null : code, on, pageable);
    } else {
        return tableRepository.findAllTablesByCodeAndOn(code == null || code.isEmpty() ? null : code, on, pageable);
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
            table.setOn(false);
            table.setBill(false);
            table.setPay(false);
            table.setCallEmployee(false);
            tableRepository.save(table);
        });
    }

    @Override
    @Transactional
    public void hardDeleteTable(Long id) {
        // Bước 1: Xóa các chi tiết hóa đơn (BillDetail) liên quan đến bảng cần xóa
        billDetailRepository.deleteBillDetailsByTableId(id);

        // Bước 2: Xóa các hóa đơn (Bill) liên quan đến bảng cần xóa
        billRepository.deleteBillsByTableId(id);

        // Bước 3: Xóa bảng chính từ bảng Tables
        tableRepository.deleteById(id);
    }
    @Override
    public Tables updateTableStatus(Long id) {
        Tables table = tableRepository.findById(id).orElseThrow(() -> new RuntimeException("Table not found"));
        table.setOn(false);
        table.setBill(true);
        return tableRepository.save(table);
    }

    @Override
    public Page<Tables> getTablesByIsOn(boolean isOn, Pageable pageable) {
        return null;
    }

    @Override
    public List<Tables> getAllTablesByClient() {
        return tableRepository.findAll();
    }

    @Override
    public Tables updateTableStatus1(Long id) {
        Tables table = tableRepository.findById(id).orElseThrow(() -> new RuntimeException("Table not found"));
        table.setPay(true);
        return tableRepository.save(table);
    }

    @Override
    public Page<Tables> findByState(String state, Pageable pageable) {
        return tableRepository.findByStateContainingIgnoreCase(state, pageable);
    }

    @Override
    public Tables updateTableStatusBill(Long id) {
        Tables table = tableRepository.findById(id).orElseThrow(() -> new RuntimeException("Table not found"));
        table.setBill(true);
        return tableRepository.save(table);
    }

    @Override
    public Tables getTableCurrent(Long tableId) {
        return tableRepository.findByTableId(tableId);
    }

    @Override
    public boolean isTableBill(Long tableId) {
        Tables table = getTableCurrent(tableId);
        return table != null && table.isBill();
    }

    @Override
    public Tables callEmployee(Long id) {
        Tables table = getTableCurrent(id);
        table.setCallEmployee(true);
        return tableRepository.save(table);
    }
}
