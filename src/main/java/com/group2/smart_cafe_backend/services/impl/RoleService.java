package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.Role;
import com.group2.smart_cafe_backend.repositories.IRoleRepository;
import com.group2.smart_cafe_backend.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
