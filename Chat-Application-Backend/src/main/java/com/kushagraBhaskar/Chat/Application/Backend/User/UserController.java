package com.kushagraBhaskar.Chat.Application.Backend.User;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findByUserId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByUserId(id));
    }

    @GetMapping("/find")
    public ResponseEntity<List<UserDTO>> findByUserName(@RequestParam String userName){
        return ResponseEntity.ok(userService.findByUserName(userName));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/exists")
    public ResponseEntity<Boolean> checkIfUserNameExist(@RequestParam String userName){
        return ResponseEntity.status(HttpStatus.OK).body(userService.existsByUserName(userName));
    }

}
