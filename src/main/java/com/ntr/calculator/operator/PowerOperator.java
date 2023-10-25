package com.ntr.calculator.operator;

public class PowerOperator implements Operator {

    @Override
    public Character getSymbol() {
        return '^';
    }

    @Override
    public Integer getPrecedence() {
        return 3;
    }
}
