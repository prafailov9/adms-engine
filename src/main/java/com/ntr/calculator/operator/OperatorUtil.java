package com.ntr.calculator.operator;

public class OperatorUtil {

    public static boolean isOperator(final char c) {
        return c == '%' || c == '/' || c == '*' || c == '+' || c == '-';
    }

}
