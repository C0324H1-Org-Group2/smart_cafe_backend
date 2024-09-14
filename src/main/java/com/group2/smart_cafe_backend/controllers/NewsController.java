package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.dtos.NewsDTO;
import com.group2.smart_cafe_backend.models.News;
import com.group2.smart_cafe_backend.models.User;
import com.group2.smart_cafe_backend.services.IFirebaseStorageService;
import com.group2.smart_cafe_backend.services.INewsService;
import com.group2.smart_cafe_backend.services.INewsUserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<News>> getAllActiveNews() {
        List<News> allNews = newsService.findAllActiveNews();
        return new ResponseEntity<>(allNews, HttpStatus.OK);
    }

    @GetMapping("/{newsId}")
    public News getNewsById(@PathVariable Long newsId) {
        return newsService.getNewsById(newsId);
    }

    @PostMapping("/create")
    public ResponseEntity<News> createNews(
            @Valid @ModelAttribute NewsDTO newsDTO,
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId
    ) throws IOException {

        String imageUrl = firebaseStorageService.uploadFile(file);

        User creator = newsUserService.getUserById(userId);

        News news = new News();
        news.setTitle(newsDTO.getTitle());
        news.setContent(newsDTO.getContent());
        news.setImageUrl(imageUrl);
        news.setPublishDate(LocalDateTime.now());
        news.setCreator(creator);

        News savedNews = newsService.save(news);

        messagingTemplate.convertAndSend("/topic/news", savedNews);

        return new ResponseEntity<>(savedNews, HttpStatus.CREATED);
    }

    @DeleteMapping("/soft-delete/{newsId}")
    public ResponseEntity<Void> softDeleteNews(@PathVariable Long newsId) {
        Optional<News> newsOptional = newsService.findById(newsId);
        if (newsOptional.isPresent()) {
            newsService.softDeleteNews(newsId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{newsId}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long newsId) {
        Optional<News> newsOptional = newsService.findById(newsId);
        if (newsOptional.isPresent()) {
            newsService.deleteNews(newsId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{newsId}")
    public ResponseEntity<News> updateNews(@PathVariable Long newsId,
                                           @Valid @ModelAttribute NewsDTO newsDTO,
                                           @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Optional<News> optionalNews = newsService.findById(newsId);
        if (optionalNews.isPresent()) {
            News existingNews = optionalNews.get();

            existingNews.setTitle(newsDTO.getTitle());
            existingNews.setContent(newsDTO.getContent());

            if (file != null && !file.isEmpty()) {
                String imageUrl = firebaseStorageService.uploadFile(file);
                existingNews.setImageUrl(imageUrl);
            }

            News updatedNews = newsService.save(existingNews);

            return new ResponseEntity<>(updatedNews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
