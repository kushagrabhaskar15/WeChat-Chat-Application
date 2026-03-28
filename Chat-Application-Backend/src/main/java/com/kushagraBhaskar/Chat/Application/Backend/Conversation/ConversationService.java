package com.kushagraBhaskar.Chat.Application.Backend.Conversation;

import com.kushagraBhaskar.Chat.Application.Backend.Message.*;
import com.kushagraBhaskar.Chat.Application.Backend.User.User;
import com.kushagraBhaskar.Chat.Application.Backend.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final MessageService messageService;
    private final ModelMapper modelMapper;

    public boolean existsById(Long conversationId){
        return conversationRepository.existsById(conversationId);
    }

    public ConversationDTO setUpConversation(List<Long> userIds) {
        Conversation conversation = new Conversation();
        List<User> users = userRepository.findAllById(userIds);
        conversation.getUsers().addAll(users);
        users.forEach(user -> user.getConversations().add(conversation));
        Conversation newConversation = conversationRepository.save(conversation);
        userRepository.saveAll(users);
        return modelMapper.map(newConversation, ConversationDTO.class);
    }

    public ConversationDTO addUser(Long conversationId,String userName){
        Conversation conversation = conversationRepository
                .findById(conversationId)
                .orElseThrow(()->new RuntimeException("No Conversation with the entered ID!"));

        User user = userRepository.findByUserNameIgnoreCase(userName);

        if(user==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No User With the entered userName!");

        user.getConversations().add(conversation);
        conversation.getUsers().add(user);

        userRepository.save(user);
        Conversation newConversation = conversationRepository.save(conversation);
        return modelMapper.map(newConversation,ConversationDTO.class);
    }

    public List<MessageDTO> getMessagesByConversationId(Long conversationId) {

        if(!existsById(conversationId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Conversation with the entered Id!");

        return messageService.getMessagesByConversationId(conversationId);
    }
}
