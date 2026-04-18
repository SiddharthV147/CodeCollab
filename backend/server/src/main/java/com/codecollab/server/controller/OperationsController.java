package com.codecollab.server.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class OperationsController {
    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public String processMessage(String message) {
        System.out.println(message);
        return "Received: " + message;
    }
}
