package com.kushagraBhaskar.Chat.Application.Backend.Conversation;

import com.kushagraBhaskar.Chat.Application.Backend.User.User;
import com.kushagraBhaskar.Chat.Application.Backend.User.UserDTO;
import com.kushagraBhaskar.Chat.Application.Backend.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConversationService {

    //CHECK FOR ERRORS

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public boolean existsById(Long conversationId){
        return conversationRepository.existsById(conversationId);
    }

    public ConversationDTO setUpConversation(List<User> users) {
        Conversation conversation = new Conversation();
        conversation.setUsers(users);
        Conversation newConversation = conversationRepository.save(conversation);
        return modelMapper.map(newConversation, ConversationDTO.class);
    }

    public ConversationDTO addUser(Long conversationId,String userName){

        if(!userRepository.existsByUserName(userName)) {
            throw new IllegalArgumentException("No User with the entered Id!");
        }

        Conversation conversation = conversationRepository
                .findById(conversationId)
                .orElseThrow(()->new RuntimeException("No Conversation with the entered ID!"));

        User user = userRepository.findByUserNameIgnoreCase(userName);
        conversation.getUsers().add(user);

        Conversation newConversation = conversationRepository.save(conversation);
        return modelMapper.map(newConversation,ConversationDTO.class);
    }
}
