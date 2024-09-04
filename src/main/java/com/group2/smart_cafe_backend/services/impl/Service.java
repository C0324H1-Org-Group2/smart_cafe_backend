package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.Tables;
import com.group2.smart_cafe_backend.repositories.IRepository;
import com.group2.smart_cafe_backend.services.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Service implements IService {
    @Autowired
    private IRepository repository;


    @Override
    public List<Tables> findAllTable() {
        return repository.findAll();
    }
}
