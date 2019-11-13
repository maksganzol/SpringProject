package com.tsurkan.exception;

public class SubjectNotFoundException extends Exception {
    private String message;
    public SubjectNotFoundException(String message) {
        this.message = "There are no subject '" + message + "'";
    }

    public SubjectNotFoundException() {
        message = "There are no such subject.";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
