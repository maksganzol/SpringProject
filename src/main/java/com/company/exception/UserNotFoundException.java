package com.company.exception;

public class UserNotFoundException extends Exception{
    String message;
    public UserNotFoundException(){
        message = "There are no user with such login";
    }

    public UserNotFoundException(String message){
        this.message = "There are no user with login '" + message + "'";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
