package com.company.service;

import com.company.dao.UserDao;
import com.company.entities.User;
import com.company.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private Map<String, User> sessionToUser;

    public UserServiceImpl() {
        this.sessionToUser = new HashMap<>();
    }

    @Override
    public void addUser(User user) {

        userDao.add(user);
    }

    @Override
    public User getUserByLogin(String login) throws UserNotFoundException {
        User user = userDao.getByLogin(login);
        if(user == null) throw new UserNotFoundException(login);
        return user;
    }

    public boolean signIn(String login, String password) throws UserNotFoundException {
        User user = userDao.getByLogin(login);
        if(user == null) throw new UserNotFoundException(login);
        return user.getPassword().equals(password);
    }

    @Override
    public User getUserBySessionId(String sessionId) {
        return null;
    }

    @Override
    public void addSession(String session, User user) {

    }

    @Override
    public void deleteSession(String session) {

    }
}
