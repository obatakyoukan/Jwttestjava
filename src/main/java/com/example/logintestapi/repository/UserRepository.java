package com.example.logintestapi.repository;

import com.example.logintestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    List<User> findByUserId(String user_id);
}
