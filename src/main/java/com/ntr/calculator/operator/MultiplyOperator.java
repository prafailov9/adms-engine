package com.ntr.calculator.operator;

public class MultiplyOperator implements Operator {
    @Override
    public Character getSymbol() {
        return '*';
    }

    @Override
    public Integer getPrecedence() {
        return 2;
    }
}
