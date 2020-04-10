package com.soul.service;

import com.soul.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    String saveOrUpdate(User user);

    List<User> find(String name);

    String login(User user);
}
