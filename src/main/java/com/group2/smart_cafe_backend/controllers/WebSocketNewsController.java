package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.News;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketNewsController {

    @MessageMapping("/news")
    @SendTo("/topic/news")
    public News sendNews(News news) {
        return news;
    }
}
