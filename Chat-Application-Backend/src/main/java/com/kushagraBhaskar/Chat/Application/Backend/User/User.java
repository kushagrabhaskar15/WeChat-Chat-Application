package com.kushagraBhaskar.Chat.Application.Backend.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Column(unique = true)
    private String userName;
    private String name;
    private String password;
}
