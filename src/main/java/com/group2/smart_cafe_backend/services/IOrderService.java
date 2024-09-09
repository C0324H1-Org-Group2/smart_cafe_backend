package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.dtos.OrderDTO;
import com.group2.smart_cafe_backend.dtos.OrderDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {
//    List<OrderDTO> findAllOrders(String codeSearch, LocalDate dateCreate);

    Page<OrderDTO> findAllOrders(String codeSearch, LocalDate dateCreate, Pageable pageable);

//    List<OrderDetailDTO> getOrderDetailsByBillId(Integer billId);

    List<OrderDetailDTO> getOrderDetailsByBillCode(String billCode);
}
