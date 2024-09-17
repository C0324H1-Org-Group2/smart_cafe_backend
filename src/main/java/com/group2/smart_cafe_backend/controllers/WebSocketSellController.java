package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.Tables;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

public class WebSocketSellController {

    @MessageMapping("/menu")
    @SendTo("/topic/admin/sell/order")
    public Tables sendOdrder(Tables tables) {
        return tables;
    }

    @MessageMapping("/menu")
    @SendTo("/topic/admin/sell/callEmployee")
    public Tables sendEmployee(Tables tables) {
        return tables;
    }

    @MessageMapping("/menu")
    @SendTo("/topic/admin/sell/pay")
    public Tables sendPay(Tables tables) {
        return tables;
    }
}
