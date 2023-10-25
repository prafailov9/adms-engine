package com.ntr.calculator.operator;

public class MinusOperator implements Operator {
    @Override
    public Character getSymbol() {
        return '-';
    }

    @Override
    public Integer getPrecedence() {
        return 1;
    }
}
