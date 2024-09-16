package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.Tables;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

public class WebSocketSellController {

    @MessageMapping("/menu")
    @SendTo("/topic/admin/sell")
    public Tables sendSell(Tables tables) {
        return tables;
    }

}
