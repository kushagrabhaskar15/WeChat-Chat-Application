package com.kushagraBhaskar.Chat.Application.Backend.Conversation;

import com.kushagraBhaskar.Chat.Application.Backend.Message.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversation")
@RequiredArgsConstructor
public class ConversationController {
    private final ConversationService conversationService;

    @PostMapping("/new")
    public ResponseEntity<ConversationDTO> setUpConversation(@RequestBody List<Long> users){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(conversationService.setUpConversation(users));
    }

    @PostMapping("/addUser")
    public ResponseEntity<ConversationDTO> addUser(
            @RequestParam Long conversationId,
            @RequestParam String userName)
    {
        return ResponseEntity.ok(conversationService.addUser(conversationId,userName));
    }

    @GetMapping("/{conversationId}")
    public ResponseEntity<List<MessageDTO>> getMessagesByConversationId(@PathVariable Long conversationId){
        return ResponseEntity.ok(conversationService.getMessagesByConversationId(conversationId));
    }

}
