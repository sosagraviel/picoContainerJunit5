package com.refactorizando.postman.example.exeptions;

public class MissingRentCarException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;
    public MissingRentCarException(String message) {
        super(null, message, "MissingRentCard", "MissingRentCard");
    }
    public MissingRentCarException() {
        super(null, "MissingRentCardException!", "MissingRentCardException", "MissingRentCardException");
    }
}
