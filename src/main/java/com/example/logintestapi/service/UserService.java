
package com.example.logintestapi.service;

import com.example.logintestapi.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByName(String name);
}