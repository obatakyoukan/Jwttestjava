package com.example.logintestapi.controller;

import com.example.logintestapi.entity.User;
import com.example.logintestapi.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    MyUserService myUserService;

    @GetMapping("/all")
    public List<User> allList()
    {
        return myUserService.searchAll();
    }

    @GetMapping("/add")
    public List<User> addUser(@RequestParam("userId") String userId, @RequestParam("password") String password, @RequestParam("name") String name)
    {
        myUserService.addUser(userId,password,name);
        return myUserService.searchAll();
    }

    @GetMapping("/del")
    public List<User> delUser(@RequestParam("userId") String userId)
    {
        myUserService.deleteUser(userId);
        return myUserService.searchAll();
    }



}
