package com.refactorizando.postman.example.exeptions;

public class UserExistException extends BadRequestAlertException {

    private final long serialVersionUID = 1L;
    public UserExistException(String message) {
        super(null, message, "UserExistException", "UserExistException");
    }
    public UserExistException() {
        super(null, "UserExistException!", "UserExistException", "UserExistException");
    }
}
