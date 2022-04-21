package com.example.logintestapi.controller;

import com.example.logintestapi.entity.User;
import com.example.logintestapi.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/all")
    public List<User> allList()
    {
        return userService.searchAll();
    }

    @GetMapping("/add")
    public List<User> addUser(@RequestParam("userId") String userId, @RequestParam("password") String password, @RequestParam("name") String name)
    {
        userService.addUser(userId,password,name);
        return userService.searchAll();
    }

    @GetMapping("/del")
    public List<User> delUser(@RequestParam("userId") String userId)
    {
        userService.deleteUser(userId);
        return userService.searchAll();
    }



}
