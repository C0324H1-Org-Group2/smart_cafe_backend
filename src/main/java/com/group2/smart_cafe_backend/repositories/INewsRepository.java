package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INewsRepository extends JpaRepository<News, Long> {
}
