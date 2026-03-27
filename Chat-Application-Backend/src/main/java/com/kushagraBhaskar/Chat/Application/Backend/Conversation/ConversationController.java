package com.kushagraBhaskar.Chat.Application.Backend.Conversation;

import com.kushagraBhaskar.Chat.Application.Backend.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/conversation")
@RequiredArgsConstructor
public class ConversationController {
    private final ConversationService conversationService;
    //CHECK FOR ERRORS
    @PostMapping("/new")
    public ResponseEntity<ConversationDTO> setUpConversation(@RequestBody List<User> users){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(conversationService.setUpConversation(users));
    }

    @PostMapping("/addUser")
    public ResponseEntity<ConversationDTO> addUser(Long conversationId,String userName){
        return ResponseEntity.ok(conversationService.addUser(conversationId,userName));
    }
}
