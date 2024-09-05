package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.Tables;

import java.util.List;

public interface IService {
    List<Tables> findAllTable();
}
