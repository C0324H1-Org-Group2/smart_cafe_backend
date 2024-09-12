package com.group2.smart_cafe_backend.repositories;
import com.group2.smart_cafe_backend.models.Employee;
import com.group2.smart_cafe_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);

    void deleteById(Long userId);


    User findByEmployee(Employee employee);



  }


