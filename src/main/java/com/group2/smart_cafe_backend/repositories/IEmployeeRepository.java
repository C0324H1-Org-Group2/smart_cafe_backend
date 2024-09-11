package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmail(String email);
}
