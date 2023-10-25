package com.ntr.calculator.tokenizer;

public enum TokenType {

    NUMBER(1),
    VARIABLE(2),
    OPERATOR(3),
    FUNCTION(4),
    CLOSE_PARENTHESIS(5),
    OPEN_PARENTHESIS(6);

    private final int value;

    // Constructor for TokenType enum
    private TokenType(int value) {
        this.value = value;
    }

    // Getter method to access the enum value
    public int getValue() {
        return value;
    }
}

