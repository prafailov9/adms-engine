package com.ntr.calculator.exception;

public class NoSuchOperatorException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Given operator %s does not exist";

    private String message;

    public NoSuchOperatorException(final char operator) {
        message = String.format(MESSAGE_TEMPLATE, operator);
    }

    @Override
    public String getMessage() {
        return message;
    }


}
