package com.ntr.jvcalc.tokenizer;

public class Token {


    private final String value;
    private final TokenType type;


    private Token(String value, TokenType type) {
        this.value = value;
        this.type = type;
    }

    public static Token of (final String value, final TokenType tokenType) {
        return new Token(value, tokenType);
    }

    public String getValue() {
        return value;
    }

    public TokenType getTokenType() {
        return type;
    }

}
