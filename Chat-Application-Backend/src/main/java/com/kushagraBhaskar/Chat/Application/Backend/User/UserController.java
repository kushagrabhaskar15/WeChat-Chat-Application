package com.kushagraBhaskar.Chat.Application.Backend.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
}
