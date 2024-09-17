package com.group2.smart_cafe_backend.controllers;

import com.group2.smart_cafe_backend.models.Feedback;
import com.group2.smart_cafe_backend.models.Tables;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.List;

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

    @MessageMapping("/menu")
    @SendTo("/topic/admin/feedback")
    public Feedback sendFeedback(Feedback feedback) {return feedback;}

    @MessageMapping("/admin/sell")
    @SendTo("/topic/client/order")
    public List<Tables> sendAllTables(List<Tables> tables) {return tables;}

    @MessageMapping("/admin/sell")
    @SendTo("/topic/client/callEmployee")
    public Tables sendAllTables(Tables tables) {return tables;}
}
