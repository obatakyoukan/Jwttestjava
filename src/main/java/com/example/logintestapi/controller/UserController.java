package com.example.logintestapi.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.logintestapi.entity.User;
import com.example.logintestapi.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Value("${security.secret-key:secret}")
    private String secretKey = "secret";

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

    @GetMapping("/decode")
    public User decode(HttpServletRequest request, HttpSession session)
    {
        String token = decodeByUTF8(request.getHeader("Authorization")).substring(7);

        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        //System.out.println(token);
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String username = jwt.getClaim("X-NAME").asString();
            String email = jwt.getClaim("X-EMAIL").asString();
            //System.out.println(username);
            //System.out.println(email);
            Optional<User> user = userService.findByEmail(email);
            return user.get();
        }catch (
                JWTVerificationException e
        ){
            e.printStackTrace();
            return null;
        }
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

    private String decodeByUTF8(String str){
        String afterStr = null;
        try{
            afterStr = URLDecoder.decode(str, "UTF-8");
        }catch(UnsupportedEncodingException ex){
            System.err.println(ex);
        }
        return afterStr;
    }

}
