package com.example.logintestapi.service;


import com.example.logintestapi.config.WebSecurityConfig;
import com.example.logintestapi.entity.MyUserDetails;
import com.example.logintestapi.entity.User;
import com.example.logintestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;




    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException
    {
        List<User> users = userRepository.findByUserId(userId);
        if( users.size() == 0 ){
            throw new UsernameNotFoundException(userId + "が存在しません");
        }
        return new MyUserDetails( users.get(0) );
    }

    public List<User> searchAll()
    {
        return userRepository.findAll();
    }

    public void addUser(String userId, String password, String name)
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUserId(userId);
        String hash = bCryptPasswordEncoder.encode(password);
        user.setPassword(hash);
        user.setName(name);
        userRepository.save(user);
    }

    public void deleteUser(String userId)
    {
        List<User> users = userRepository.findByUserId(userId);
        userRepository.deleteAll(users);
    }

}
