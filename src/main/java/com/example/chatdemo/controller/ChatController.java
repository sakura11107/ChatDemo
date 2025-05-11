package com.example.chatdemo.controller;


import com.example.chatdemo.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void send(@Payload ChatMessage message) {
        System.out.println("Sending message: " + message);

        String destination = "/topic/messages/" + message.getRegion();
        messagingTemplate.convertAndSend(destination, message);
    }
}
