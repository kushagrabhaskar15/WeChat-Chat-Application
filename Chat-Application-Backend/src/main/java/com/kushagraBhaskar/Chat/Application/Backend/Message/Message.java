package com.kushagraBhaskar.Chat.Application.Backend.Message;

import com.kushagraBhaskar.Chat.Application.Backend.Conversation.Conversation;
import com.kushagraBhaskar.Chat.Application.Backend.Enums.MessageType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String content;
    private LocalDateTime MessageSendDateTime;
    @EnumeratedValue
    private MessageType type;
    private String sender;

    @ManyToOne
    private Conversation conversation;
}
