package com.codecollab.server.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.simpMessagingTemplate = messagingTemplate;
    }

    public void sendMessage(String userId, String message) {
        simpMessagingTemplate.convertAndSendToUser(userId, "/queue/reply", message);
    }
}
