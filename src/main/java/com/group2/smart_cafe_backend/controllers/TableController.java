package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.services.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/tables")
public class TableController {

    @Autowired
    private ITableService serviceTable;

    // Lấy tất cả các bàn chưa bị xóa
    @GetMapping
    public ResponseEntity<Page<Tables>> getAllTables(
            @RequestParam(value = "code", defaultValue = "") String code,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tables> tables = serviceTable.getAllTables(code, pageable);
        return ResponseEntity.ok(tables);
    }

    // Lấy bàn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Tables> getTableById(@PathVariable Long id) {
        Optional<Tables> table = serviceTable.getTableById(id);
        return table.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByState")
    public ResponseEntity<?> getTablesByState(@RequestParam String state, @RequestParam int page, @RequestParam int size) {
        if (state.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Please enter a keyword!");
        }
        Page<Tables> tables = serviceTable.findByState(state, PageRequest.of(page, size));
        if (tables.hasContent()) {
            return ResponseEntity.ok(tables);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
        }
    }
    // Tạo mới bàn
    @PostMapping("/create")
    public ResponseEntity<Tables> createTable(@RequestBody Tables table) {
        try {
            Tables createdTable = serviceTable.createTable(table);
            return ResponseEntity.ok(createdTable);
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi chi tiết vào log
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Cập nhật bàn
    @PutMapping("/{id}")
    public ResponseEntity<Tables> updateTable(@PathVariable Long id, @RequestBody Tables table) {
        Tables updatedTable = serviceTable.updateTable(id, table);
        return ResponseEntity.ok(updatedTable);
    }

    // Xóa mềm bàn theo ID
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<Void> softDeleteTable(@PathVariable Long id) {
        serviceTable.softDeleteTable(id);
        return ResponseEntity.noContent().build();
    }

    // Xóa cứng bàn theo ID
    @DeleteMapping("/hard/{id}")
    public ResponseEntity<Void> hardDeleteTable(@PathVariable Long id) {
        serviceTable.hardDeleteTable(id);
        return ResponseEntity.noContent().build();
    }
}
