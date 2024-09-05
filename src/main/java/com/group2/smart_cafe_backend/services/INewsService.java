package com.group2.smart_cafe_backend.services;

import com.group2.smart_cafe_backend.models.News;

import java.util.List;

public interface INewsService {
    List<News> getAllNews();

    News getNewsById(Long newsId);
}
