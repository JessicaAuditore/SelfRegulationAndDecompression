package com.soul.service.impl;

import com.soul.dao.UserDao;
import com.soul.entity.User;
import com.soul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public String saveOrUpdate(User user) {
        userDao.saveAndFlush(user);
        return "1";
    }

    @Override
    public String login(User user) {
        List<User> u = userDao.findAllByName(user.getName());
        if (u.size() == 0) {
            return "1";
        } else if (!u.get(0).getPassword().equals(user.getPassword())) {
            return "2";
        } else {
            return "3";
        }
    }

    @Override
    public List<User> find(String name) {
        return userDao.findAllByNameLike(name);
    }
}
