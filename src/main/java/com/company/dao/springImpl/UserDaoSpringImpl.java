package com.company.dao.springImpl;

import com.company.dao.UserDao;
import com.company.dao.springImpl.rowMapper.UserRowMapper;
import com.company.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoSpringImpl implements UserDao {


    @Autowired
    private JdbcTemplate template;

    @Override
    public void add(User user) {
        String query = "INSERT INTO users (login, password) VALUES('" + user.getLogin() + "', '" + user.getPassword() + "')";
        template.execute(query);
    }

    @Override
    public User get(int id) {
        String query = "SELECT * FROM users WHERE idusers=" + id;
        return template.queryForObject(query, new UserRowMapper());
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users";
        return template.query(query, new UserRowMapper());
    }

    @Override
    public User getByLogin(String login) {
        String query = "SELECT * FROM users WHERE login='" + login + "'";
        return template.queryForObject(query, new UserRowMapper());
    }
}
