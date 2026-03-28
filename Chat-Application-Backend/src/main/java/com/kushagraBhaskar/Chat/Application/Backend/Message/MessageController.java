package com.kushagraBhaskar.Chat.Application.Backend.Message;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    @MessageMapping("/message.sendMessage/{conversationId}")
    public void sendMessage(
            @Payload MessageDTO message,
            @DestinationVariable Long conversationId
    ){
        messageService.saveMessage(message,conversationId);

        messagingTemplate.convertAndSend(
                "/topic/conversation/"+conversationId,
                message
        );
    }

    @MessageMapping("/message.addUser/{conversationId}")
    @SendTo("/topic/public")
    public void userLeft(
            @Payload MessageDTO message,
            @DestinationVariable Long conversationId,
            SimpMessageHeaderAccessor headerAccessor)
    {
        headerAccessor.getSessionAttributes().put("UserName",message.getSender());
        messagingTemplate.convertAndSend(
                "/topic/conversation/"+conversationId,
                message
        );
    }



}
