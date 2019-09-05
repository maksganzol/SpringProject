package com.company.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class Executer {

    private Connection connection;

    @Autowired
    public Executer(DataSource dataSource){
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public Executer(Connection connection){
        this.connection = connection;
    }

    public void update(String query){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> T query(String query, ResultHandler<T> handler){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            T value = handler.handle(resultSet);
            resultSet.close();
            statement.close();
            return value;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
