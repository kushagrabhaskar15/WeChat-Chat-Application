package com.kushagraBhaskar.Chat.Application.Backend.User;

import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public boolean existsByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    public UserDTO addUser(UserDTO userDTO){
        User user = new User();
        if(existsByUserName(userDTO.getUserName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User with same userName already exist!");
        }
        user.setUserName(userDTO.getUserName());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        User newUser = userRepository.save(user);
        return modelMapper.map(user,UserDTO.class);
    }

    public UserDTO findByUserId(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("No User with the entered ID!"));

        return modelMapper.map(user,UserDTO.class);
    }

    public List<UserDTO> findByUserName(String userName) {
        List<User> users = userRepository.findByUserNameContainingIgnoreCase(userName);
        return users
                .stream()
                .map(user->modelMapper.map(user,UserDTO.class))
                .toList();
    }

    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user->modelMapper.map(user, UserDTO.class))
                .toList();
    }
}
