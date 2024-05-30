package com.ntr.jvcalc.tokenizer;

public enum Precedence {

    ADD('+', 1), SUB('-', 1), MULT('*', 2), DIV('/', 2), POW('*', 3);

    private final char symbol;
    private final int val;

    Precedence(final char symbol, final int val) {
        this.symbol = symbol;
        this.val = val;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getVal() {
        return val;
    }

}
