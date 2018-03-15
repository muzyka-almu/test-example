package com.example.testing.service;

import com.example.testing.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private UserDao userDao;
    private TestService testService;

    @Autowired
    public UserService(UserDao userDao, TestService testService) {
        this.userDao = userDao;
        this.testService = testService;
    }

    public UserService() {
    }

    public void save() {
        System.out.println("UserService.save");
        System.out.println("UserService.save.userDao " + userDao);
        System.out.println("UserService.save.testService " + testService);
    }
}
