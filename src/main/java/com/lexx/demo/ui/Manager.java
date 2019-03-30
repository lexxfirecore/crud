package com.lexx.demo.ui;

import java.security.SecureRandom;
import java.util.Scanner;
import java.util.logging.Level;
import org.apache.commons.lang.RandomStringUtils;

import com.lexx.demo.entity.User;
import com.lexx.demo.service.BusinessService;
import com.lexx.demo.service.DBLoginException;

/**
 * Created by alexandruco on 28-Mar-19.
 */


public class Manager {

    public static String username;
    public static String password;

    public static void main(String[] args) throws DBLoginException {
        // clean log from warnings
        @SuppressWarnings("unused")
        org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); //or whatever level you need

        populateTables();

        enterUser();

        printAllUsers();

        User user = BusinessService.readUserById(3, password);
        System.out.println(user);
    }


    private static void printAllUsers() {
        System.out.println("All users: ");
        try {
            for (User u : BusinessService.readAll()) {
                System.out.println(u);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void populateTables() throws DBLoginException {
        String[] names = new String[]{"alpha", "beta", "gamma", "delta"};
        for (String name : names) {
            BusinessService.createUser(new User(name, generateString()));
        }
    }

    private static String generateString() {
        char[] posChars = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
        int randomStrLength = 6;
        return RandomStringUtils.random(randomStrLength, 0, posChars.length - 1, false, false, posChars, new SecureRandom());
    }

    private static void enterUser() throws DBLoginException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = in.nextLine();
        System.out.println("username: " + username);

        System.out.println("Enter password:");
        String password = in.nextLine();
        System.out.println("password: " + password);
        final int createdUserId = BusinessService.createUser(new User(username, password));
        System.out.println(createdUserId);
    }
}
