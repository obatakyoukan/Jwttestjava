package com.example.logintestapi.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="user")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    //Unique
    @Column(name="userId", unique = true )
    private String userId;

    @Column(name="password")
    private String password;
    @Column(name="name")
    private String name;
}
