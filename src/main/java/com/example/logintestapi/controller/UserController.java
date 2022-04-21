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

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/all")
    public List<User> allList()
    {
        return userService.searchAll();
    }

    @PostMapping("/add")
    public List<User> addUser(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("email") String email)
    {
        userService.addUser(name,password,email);
        return userService.searchAll();
    }

    @PostMapping("/del")
    public List<User> delUser(@RequestParam("email") String email)
    {
        userService.deleteUser(email);
        return userService.searchAll();
    }



}
