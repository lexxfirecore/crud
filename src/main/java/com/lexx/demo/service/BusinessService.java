package com.lexx.demo.service;

import java.util.List;

import com.lexx.demo.entity.User;

/**
 * Created by alexandruco on 28-Mar-19.
 */
public class BusinessService {

    public static User readUserById(int id, String password) {
        User user = null;
        try {
            user = UserService.readById(id, password);

        } catch (DBLoginException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public static User readUserByName(String username, String password) {
        User user = null;
        try {
            user = UserService.readByName(username, password);

        } catch (DBLoginException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public static List<User> readAll() {
        return UserService.readAll();
    }

    public static int createUser(User user) throws DBLoginException {
        int id = user.getId();
        if ( ! userExists(user)) {
            id = UserService.create(user);
        }
        return id;
    }

    private static boolean userExists(User user) throws DBLoginException {
        User userDb = UserService.readByName(user.getName(), user.getPassword());
        return ( userDb != null) ? true : false;
    }
}
