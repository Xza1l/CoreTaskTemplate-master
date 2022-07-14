package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Mike", "Il", (byte) 5);
        userService.saveUser("Mike2", "Il", (byte) 5);
        userService.saveUser("Mike3", "Il", (byte) 5);
        userService.saveUser("Mike4", "Il", (byte) 5);
        userService.saveUser("Mike5", "Il", (byte) 5);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers());

    }
}
