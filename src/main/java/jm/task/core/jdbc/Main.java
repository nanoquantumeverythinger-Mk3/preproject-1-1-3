package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl usi = new UserServiceImpl();
        usi.dropUsersTable();
        usi.createUsersTable();
        usi.saveUser("a","a",(byte) 1);
        usi.saveUser("b","b",(byte) 2);
        usi.saveUser("c","c",(byte) 3);
        List<User> list = usi.getAllUsers();
        for (User u: list) {
            System.out.println(u);
        }
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
