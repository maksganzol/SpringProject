package com.tsurkan.service;

import com.tsurkan.entities.User;
import com.tsurkan.exception.UserNotFoundException;

public interface UserService {
    void addUser(User user);
    User getUserByLogin(String login) throws UserNotFoundException;
    User getUserBySessionId(String sessionId);
    void addSession(String session, User user);
    void deleteSession(String session);
    boolean signIn(String login, String password) throws UserNotFoundException;
}
