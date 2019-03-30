package com.lexx.demo.service;

import java.util.List;

import org.postgresql.util.PSQLException;

import com.lexx.demo.dao.UserDao;
import com.lexx.demo.entity.User;

/**
 * Created by alexandruco on 28-Mar-19.
 */
public class UserService {
    public static User readById(int id, String password) throws DBLoginException {
        return UserDao.read(id, password);
    }

    public static User readByName(String username, String password) throws DBLoginException {
        User user = UserDao.read(username, password);
        return user;
    }

    public static List<User> readAll() {
        return UserDao.readAll();
    }

    public static int create(User user) {
        return UserDao.create(user);
    }
}
