package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.News;
import com.group2.smart_cafe_backend.services.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private INewsService newsService;

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        List<News> allNews = newsService.getAllNews();
        return new ResponseEntity<>(allNews, HttpStatus.OK);
    }

    @GetMapping("/{newsId}")
    public News getNewsById(@PathVariable Long newsId) {
        return newsService.getNewsById(newsId);
    }
}
