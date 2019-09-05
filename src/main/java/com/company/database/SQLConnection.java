package com.company.database;

import java.sql.*;

public class SQLConnection {

    private static Connection connection;

    public static Connection getConnection() {
        if(connection==null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/consolebase", "root", "robocopr");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static boolean isDataBaseExists(String dbName){
        try {
            DatabaseMetaData metaData = getConnection().getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, dbName, null);
            if(resultSet.next())
                return true;
            else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
