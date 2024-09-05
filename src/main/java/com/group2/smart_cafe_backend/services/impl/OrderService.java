package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.dtos.OrderDTO;
import com.group2.smart_cafe_backend.dtos.OrderDetailDTO;
import com.group2.smart_cafe_backend.repositories.IOrderRepository;
import com.group2.smart_cafe_backend.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Page<OrderDTO> findAllOrders(String codeSearch, LocalDate dateCreate, Pageable pageable) {
        return orderRepository.findAllOrders("%"+ codeSearch+"%", dateCreate, pageable);
    }

    @Override
    public List<OrderDetailDTO> getOrderDetailsByBillId(Integer billId) {
        return orderRepository.findOrderDetailsByBillId(billId);
    }


//    @Override
//    public List<OrderDTO> findAllOrders(String codeSearch, LocalDate dateCreate) {
//        return orderRepository.findAllOrders("%"+ codeSearch+"%", dateCreate);
//    }


}
