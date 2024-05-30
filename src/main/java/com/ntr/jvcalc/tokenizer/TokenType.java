package com.ntr.jvcalc.tokenizer;

public enum TokenType {
    NUMBER(1), VARIABLE(2), OPERATOR(3), FUNCTION(4), OPEN_PAREN(5), CLOSE_PAREN(6);

    private final int value;

    TokenType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
