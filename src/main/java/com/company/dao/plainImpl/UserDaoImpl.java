package com.company.dao.plainImpl;

import com.company.dao.UserDao;
import com.company.database.Executer;
import com.company.database.SQLConnection;
import com.company.entities.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Executer executer;

    public UserDaoImpl(){
        executer = new Executer(SQLConnection.getConnection());
    }
    @Override
    public void add(User user) {
        String query = "INSERT INTO users (login, password) VALUES('" + user.getLogin() + "', '" + user.getPassword() + "')";
        executer.update(query);
    }

    @Override
    public User get(int id) {
        String query = "SELECT * FROM users WHERE idusers=" + id;
        return executer.query(query, resultSet -> {
            resultSet.next();
            String login = resultSet.getString("login");
            String password = resultSet.getString("password");
            return new User(login, password);
        });
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        return executer.query(query, resultSet -> {
            List<User> users = new ArrayList<>();
            while(resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                users.add(new User(login, password));
            }
            return users;
        });
    }

    @Override
    public User getByLogin(String login) {
        String query = "SELECT * FROM users WHERE login='" + login + "'";
        return executer.query(query, resultSet -> {
            User u = new User();
            if(resultSet.next()){
                u.setLogin(resultSet.getString("login"));
                u.setPassword(resultSet.getString("password"));
                return u;
            }
            return null;
        });
    }
}
