package com.kushagraBhaskar.Chat.Application.Backend.Config;

import com.kushagraBhaskar.Chat.Application.Backend.Message.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    public final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleUserDisconnect(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userName = (String) headerAccessor.getSessionAttributes().get("UserName");
        if(userName!=null){
            log.info("User Disconnected: {}",userName);
            Message chatMessage = Message.builder()
                            .sender(userName)
                            .build();
            messageTemplate.convertAndSend("/topic/public",chatMessage);
        }
    }
}
