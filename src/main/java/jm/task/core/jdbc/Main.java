package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Rita" ,"Kornienko" , (byte) 29);
        userService.saveUser("Artyr" ,"Ivanov" , (byte) 39);
        userService.saveUser("Dima" ,"Sidorov" , (byte) 19);
        userService.saveUser("Anton" ,"Lobysov" , (byte) 19);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
