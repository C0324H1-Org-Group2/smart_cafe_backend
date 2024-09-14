package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.News;

import java.util.List;
import java.util.Optional;

public interface INewsService {
    List<News> findAllActiveNews();

    News getNewsById(Long newsId);

    News save(News news);

    List<News> findTop3ByOrderByViewCountDesc();

    Optional<News> findById(Long id);

    void hardDeleteNews(Long newsId);

    void softDeleteNews(Long newsId);

    List<News> findAll();

    List<News> searchByTitle(String title);
}
