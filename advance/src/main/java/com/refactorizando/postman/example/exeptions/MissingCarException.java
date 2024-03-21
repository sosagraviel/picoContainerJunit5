package com.refactorizando.postman.example.exeptions;

public class MissingCarException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;
    public MissingCarException(String message) {
        super(null, message, "MissingCard", "MissingCard");
    }
    public MissingCarException() {
        super(null, "MissingCardException!", "MissingCardException", "MissingCardException");
    }
}
