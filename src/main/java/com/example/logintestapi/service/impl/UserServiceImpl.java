package com.example.logintestapi.service.impl;

import com.example.logintestapi.entity.User;
import com.example.logintestapi.repository.UserRepository;
import com.example.logintestapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByName(String name) {
        Objects.requireNonNull(name, "name must be not null");
        return userRepository.findFirstByName(name);
    }

    public Optional<User> findByEmail(String email){
        Objects.requireNonNull(email, "email must be not null");
        return userRepository.findByEmail(email);
    }

    public List<User> searchAll(){
        return userRepository.findAll();
    }
    public void addUser(String name, String password, String email){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hash = passwordEncoder.encode(password);
        /*
        if(passwordEncoder.matches("pass", hash))
        {
            System.out.println("you pass");
        }
        System.out.println(password);
        System.out.println(hash);
        */
        User user = User.of(name,hash,email);
        userRepository.save(user);
    }
    public void deleteUser(String email)
    {
        Optional<User> user = userRepository.findByEmail(email);
        if( user.isPresent() ){
            userRepository.delete(user.get());
        }
    }

}