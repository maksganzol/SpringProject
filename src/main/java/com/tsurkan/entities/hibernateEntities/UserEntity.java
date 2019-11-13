package com.tsurkan.entities.hibernateEntities;

public class UserEntity {
    private String login, password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserEntity() {
    }
}
