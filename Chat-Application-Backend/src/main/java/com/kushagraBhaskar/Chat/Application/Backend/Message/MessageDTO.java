package com.kushagraBhaskar.Chat.Application.Backend.Message;

import com.kushagraBhaskar.Chat.Application.Backend.Enums.MessageType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private Long messageId;
    private String content;
    private LocalDateTime MessageSendDateTime;
    private String sender;
    private MessageType type;
}
