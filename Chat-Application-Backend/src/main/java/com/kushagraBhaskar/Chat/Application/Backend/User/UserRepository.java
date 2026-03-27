package com.kushagraBhaskar.Chat.Application.Backend.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUserName(String userName);
    User findByUserNameIgnoreCase(String userName);
    List<User> findByUserNameContainingIgnoreCase(String userName);
}
