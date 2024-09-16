package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface INewsService {
    Page<News> findAllActiveNews(Pageable page);

    News getNewsById(Long newsId);

    News save(News news);

    List<News> findTop3ByOrderByViewCountDesc();

    Optional<News> findById(Long id);

    void hardDeleteNews(Long newsId);

    void softDeleteNews(Long newsId);

    Page<News> findAll(Pageable pageable);

    List<News> searchByTitle(String title);
}
