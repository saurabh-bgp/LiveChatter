package com.example.chatting.controller;

import com.example.chatting.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message sendMessage(Message message) {
        return message;
    }

    @MessageMapping("/private-message")
    public void sendPrivateMessage(Message message) {
        messagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
}
