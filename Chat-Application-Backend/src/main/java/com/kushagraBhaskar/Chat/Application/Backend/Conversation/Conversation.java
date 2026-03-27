package com.kushagraBhaskar.Chat.Application.Backend.Conversation;

import com.kushagraBhaskar.Chat.Application.Backend.Message.Message;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conversationId;

    @OneToMany
    private List<Message> messages;
}
