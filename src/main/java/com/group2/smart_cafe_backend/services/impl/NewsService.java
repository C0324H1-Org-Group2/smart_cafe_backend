package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.News;
import com.group2.smart_cafe_backend.repositories.INewsRepository;
import com.group2.smart_cafe_backend.services.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements INewsService {
    @Autowired
    private INewsRepository newsRepository;

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News getNewsById(Long newsId) {
        Optional<News> news = newsRepository.findById(newsId);
        return news.orElse(null);
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }
}
