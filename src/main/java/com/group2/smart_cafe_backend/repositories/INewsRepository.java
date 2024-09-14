package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsRepository extends JpaRepository<News, Long> {
    List<News> findTop3ByOrderByViewCountDesc();

    @Query("SELECT n FROM News n WHERE n.isDeleted = false")
    List<News> findAllActiveNews();
}
