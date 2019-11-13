package com.tsurkan.dao;

import com.tsurkan.entities.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    User get(int id);
    List<User> getAll();
    User getByLogin(String login);
}
