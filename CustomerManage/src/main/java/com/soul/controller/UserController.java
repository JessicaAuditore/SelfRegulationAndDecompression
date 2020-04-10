package com.soul.controller;

import com.soul.entity.User;
import com.soul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping(value = "/find/{name}")
    public User find(@PathVariable(value = "name") String name) {
        List<User> list = userService.find(name);
        if (list.size() == 0) {
            return new User();
        } else {
            return list.get(0);
        }
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    @ResponseBody
    @PostMapping(value = "/addOrEdit")
    public String addOrEdit(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }
}