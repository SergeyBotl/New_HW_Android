package com.example.sergey.lesson1.task2;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO {
    private static UserDAO userDAO;

    public UserDAO() {
    }

    public static UserDAO getUserDao(){
        if (userDAO==null){
             userDAO=new UserDAO();
        }
        return userDAO;
    }

    List<User> list = new ArrayList<>();

    @Override
    public void save(User user) {
        list.add(user);
    }
}
