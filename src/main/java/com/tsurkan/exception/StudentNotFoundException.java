package com.tsurkan.exception;

public class StudentNotFoundException extends Exception{
    private String message;
    public StudentNotFoundException(){
        message = "Student not found in database";
    }
    public StudentNotFoundException(String message){
        this.message = "There are no student with name '" + message + "' in database.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
