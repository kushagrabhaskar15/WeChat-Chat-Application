package com.kushagraBhaskar.Chat.Application.Backend.Message;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/message.sendMessage")
    @SendTo("/topic/public")
    public MessageDTO sendMessage(@Payload MessageDTO message){
        return message;
    }

    @MessageMapping("/message.addUser")
    @SendTo("/topic/public")
    public MessageDTO userLeft(@Payload MessageDTO message,
                                SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("UserName",message.getSender());
        return message;
    }



}
