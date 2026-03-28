package com.kushagraBhaskar.Chat.Application.Backend.Message;

import com.kushagraBhaskar.Chat.Application.Backend.Conversation.Conversation;
import com.kushagraBhaskar.Chat.Application.Backend.Conversation.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final ModelMapper modelMapper;

    public void saveMessage(MessageDTO messageDTO, Long conversationId) {
        Conversation conversation = conversationRepository
                .findById(conversationId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No Conversation Found with the entered Id!"));

        Message message = new Message();

        message.setType(messageDTO.getType());
        message.setContent(messageDTO.getContent());
        message.setSender(messageDTO.getSender());
        message.setMessageSendDateTime(LocalDateTime.now());
        message.setConversation(conversation);

        messageRepository.save(message);
    }

    public List<MessageDTO> getMessagesByConversationId(Long conversationId) {

        List<Message> messages =  messageRepository.findAllByConversation_ConversationId(conversationId);

        if(messages.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No messages in the chat!");

        return messages
                .stream()
                .map(message -> modelMapper.map(message, MessageDTO.class))
                .toList();
    }
}
