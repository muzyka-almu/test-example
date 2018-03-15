package com.example.testing.dao;

import com.example.testing.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public User findById(Integer id) {
        System.out.println("UserDao.findById");

        return new User(1, 23, "UserDao.findById");
    }

    public User findByName(String name) {
        System.out.println("UserDao.findByName");

        return new User(1, 23, "UserDao.findByName");
    }

    public boolean save(User user) {
        System.out.println("UserDao.save");

        return true;
    }

    public String getNameById(Integer id) {
        System.out.println("UserDao.getNameById");

        return "Default name";
    }
}
