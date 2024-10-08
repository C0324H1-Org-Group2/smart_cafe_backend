package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.services.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/tables")
public class TableController {
    @Autowired
    private ITableService serviceTable;
    @GetMapping
//    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Tables>> getAllTables(
            @RequestParam(value = "code", defaultValue = "") String code,
            @RequestParam(value = "on", required = false) Boolean on,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "includeDeleted", defaultValue = "false") boolean includeDeleted) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("id"))); // Sắp xếp theo id giảm dần
        Page<Tables> tables = serviceTable.getAllTables(code, on, includeDeleted, pageable);
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }
    @GetMapping("/check-code/{code}")
    public ResponseEntity<Map<String, Boolean>> checkCode(@PathVariable String code) {
        boolean exists = serviceTable.existsByCode(code);
        return ResponseEntity.ok(Collections.singletonMap("exists", exists));
    }

    @GetMapping("/byIsOn")
//    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
    public Page<Tables> getTablesByIsOn(
            @RequestParam boolean isOn,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return serviceTable.getTablesByIsOn(isOn, pageable);
    }
    // Lấy bàn theo ID
    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Tables> getTableById(@PathVariable Long id) {
        Optional<Tables> table = serviceTable.getTableById(id);
        return table.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByState")
//    @PreAuthorize("hasRole('ROLE_EMPLOYEE') or hasRole('ROLE_ADMIN')")
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
            // Kiểm tra xem mã code đã tồn tại chưa
            if (serviceTable.existsByCode(table.getCode())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Trả về lỗi 409 nếu mã code đã tồn tại
            }

            Tables createdTable = serviceTable.createTable(table);
            return ResponseEntity.ok(createdTable);
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi chi tiết vào log
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Cập nhật bàn
    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Tables> updateTable(@PathVariable Long id, @RequestBody Tables table) {
        Tables updatedTable = serviceTable.updateTable(id, table);
        return ResponseEntity.ok(updatedTable);
    }

    // Xóa mềm bàn theo ID
    @DeleteMapping("/soft/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> softDeleteTable(@PathVariable Long id) {
        serviceTable.softDeleteTable(id);
        return ResponseEntity.noContent().build();
    }

    // Xóa cứng bàn theo ID
    @DeleteMapping("/hard/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTable(@PathVariable Long id) {
        try {
            serviceTable.hardDeleteTable(id);
            return ResponseEntity.ok("Xóa bàn thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi xóa bàn: " + e.getMessage());
        }
    }

    @PatchMapping("/{tableId}/restore")
    public ResponseEntity<?> restoreTable(@PathVariable Long tableId) {
        try {
            Tables restoredTable = serviceTable.restoreTable(tableId);
            return new ResponseEntity<>(restoredTable, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi khôi phục bàn: " + e.getMessage());
        }
    }
}
