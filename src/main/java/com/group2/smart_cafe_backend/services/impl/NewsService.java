package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.News;
import com.group2.smart_cafe_backend.repositories.INewsRepository;
import com.group2.smart_cafe_backend.services.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements INewsService {
    @Autowired
    private INewsRepository newsRepository;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}
