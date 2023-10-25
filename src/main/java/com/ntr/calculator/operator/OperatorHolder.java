package com.ntr.calculator.operator;

import com.ntr.calculator.exception.NoSuchOperatorException;

import java.util.Map;

import static com.ntr.calculator.operator.OperatorUtil.isOperator;

public class OperatorHolder {

    private static final Map<Character, Operator> operatorMap;

    static {
        operatorMap = Map.of(
                '^', new PowerOperator(),
                '*', new MultiplyOperator(),
                '/', new DivisionOperator(),
                '+', new PlusOperator(),
                '-', new MinusOperator());
    }

    public static Operator getOperator(final char symbol) {
        if (!isOperator(symbol)) {
            throw new NoSuchOperatorException(symbol);
        }
        return operatorMap.get(symbol);
    }


}
