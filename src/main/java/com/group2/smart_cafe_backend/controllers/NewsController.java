package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.News;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.services.IFirebaseStorageService;
import com.group2.smart_cafe_backend.services.INewsService;
import com.group2.smart_cafe_backend.services.INewsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private INewsService newsService;
    @Autowired
    private IFirebaseStorageService firebaseStorageService;
    @Autowired
    private INewsUserService newsUserService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        List<News> allNews = newsService.getAllNews();
        return new ResponseEntity<>(allNews, HttpStatus.OK);
    }

    @GetMapping("/{newsId}")
    public News getNewsById(@PathVariable Long newsId) {
        return newsService.getNewsById(newsId);
    }

    @PostMapping("/create")
    public ResponseEntity<News> createNews(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId
    ) throws IOException {

        String imageUrl = firebaseStorageService.uploadFile(file);

        User creator = newsUserService.getUserById(userId);

        News news = new News();
        news.setTitle(title);
        news.setContent(content);
        news.setImageUrl(imageUrl);
        news.setPublishDate(LocalDateTime.now());
        news.setCreator(creator);

        News savedNews = newsService.save(news);

        messagingTemplate.convertAndSend("/topic/news", savedNews);

        return new ResponseEntity<>(savedNews, HttpStatus.CREATED);
    }

    @GetMapping("/top-viewed")
    public List<News> getTopViewedNews() {
        return newsService.findTop3ByOrderByViewCountDesc();
    }

    @PutMapping("/{newsId}/increase-views")
    public ResponseEntity<News> increaseViewCount(@PathVariable Long newsId) {
        Optional<News> optionalNews = newsService.findById(newsId);

        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            news.setViewCount(news.getViewCount() + 1);
            newsService.save(news);

            return ResponseEntity.ok(news);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
