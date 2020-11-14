package com.dbdemo.demo.service;

import com.dbdemo.demo.model.Status;
import com.dbdemo.demo.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getUsers();

    List<UserEntity> getUsersViaStatus(String status);

    UserEntity getUserById(Long id);

}
