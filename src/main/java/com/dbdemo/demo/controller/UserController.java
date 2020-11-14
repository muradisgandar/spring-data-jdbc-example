package com.dbdemo.demo.controller;

import com.dbdemo.demo.model.UserEntity;
import com.dbdemo.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/status/{status}")
    public List<UserEntity> getUserForStatus(@PathVariable String status){
        return userService.getUsersViaStatus(status);
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
