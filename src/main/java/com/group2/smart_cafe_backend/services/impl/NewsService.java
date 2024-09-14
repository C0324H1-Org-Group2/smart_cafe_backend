package com.group2.smart_cafe_backend.services.impl;

import com.group2.smart_cafe_backend.models.News;
import com.group2.smart_cafe_backend.models.emum.NewsStatus;
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
    public List<News> findAllActiveNews() {
        return newsRepository.findAllByStatus(NewsStatus.Active);
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> searchByTitle(String title) {
        return newsRepository.searchByTitle(title);
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

    @Override
    public List<News> findTop3ByOrderByViewCountDesc() {
        return newsRepository.findTop3ByOrderByViewCountDesc();
    }

    @Override
    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public void hardDeleteNews(Long newsId) {
        newsRepository.deleteById(newsId);
    }

    @Override
    public void softDeleteNews(Long newsId) {
        Optional<News> newsOptional = newsRepository.findById(newsId);
        if (newsOptional.isPresent()) {
            News news = newsOptional.get();
            news.setStatus(NewsStatus.valueOf("Deleted"));
            newsRepository.save(news);
        }
    }
}
