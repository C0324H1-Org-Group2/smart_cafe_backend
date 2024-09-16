package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.News;
import com.group2.smart_cafe_backend.models.emum.NewsStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsRepository extends JpaRepository<News, Long> {
    List<News> findTop3ByOrderByViewCountDesc();

    @Query("SELECT n FROM News n WHERE n.status = :status")
    Page<News> findAllByStatus(@Param("status") NewsStatus status, Pageable pageable);

    @Query("SELECT n FROM News n WHERE n.status = 'Deleted'")
    List<News> findAllDeletedNews();

    @Query("SELECT n FROM News n WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<News> searchByTitle(@Param("title") String title);
}
