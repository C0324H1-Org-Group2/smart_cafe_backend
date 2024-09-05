package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.dtos.OrderDTO;
import com.group2.smart_cafe_backend.dtos.OrderDetailDTO;
import com.group2.smart_cafe_backend.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

//    @GetMapping
//    public ResponseEntity<?> getAllOrders(@RequestParam(value = "codeSearch", defaultValue = "")String codeSearch,
//                                          @RequestParam(value = "dateCreate", defaultValue = "")LocalDate dateCreate) {
//        List<OrderDTO> orderDTOS = orderService.fillAllOrders();
//        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
//    }

//    @GetMapping
//    public ResponseEntity<?> getAllOrders(@RequestParam(value = "codeSearch", defaultValue = "") String codeSearch,
//                                          @RequestParam(value = "dateCreate", required = false) LocalDate dateCreate) {
//        List<OrderDTO> orderDTOS = orderService.findAllOrders(codeSearch, dateCreate);
//        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<?> getAllOrders(
            @RequestParam(value = "codeSearch", defaultValue = "") String codeSearch,
            @RequestParam(value = "dateCreate", required = false) LocalDate dateCreate,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "2") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOS = orderService.findAllOrders(codeSearch, dateCreate, pageable);
        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<List<OrderDetailDTO>> getOrderDetails(@PathVariable Integer id) {
        List<OrderDetailDTO> orderDetails = orderService.getOrderDetailsByBillId(id);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
}


