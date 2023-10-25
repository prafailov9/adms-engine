package com.ntr.calculator.tokenizer;

public class Token {

    private String value;
    private TokenType type;

    public static Token of(final String value, final TokenType type) {
        return new Token(value, type);
    }

    private Token(final String value, final TokenType tokenType) {
        this.value = value;
        this.type =  tokenType;
    }

    public String getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }

}
