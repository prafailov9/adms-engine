package com.ntr.calculator;

import com.ntr.calculator.exception.MissingExpressionException;

import java.util.ArrayList;
import java.util.List;

public class Expression {

    private final String expression;
    private List<String> infixExpressionNodes;
    private List<String> postfixExpressionNodes;


    public static Expression of(String expression) {
        // ensuring correct creation of the expression
        if (expression == null || expression.length() == 0) {
            throw new MissingExpressionException();
        }
        return new Expression(expression.trim());
    }

    private Expression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public List<String> buildInfixExpressionNodes() {
        if (expression == null || expression.isEmpty()) {
            throw new RuntimeException("Cannot build expression nodes with empty or null expression");
        }
        List<String> expressionNodes = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            // if previous and current elements are numbers - create a new node and replace it with prev one
            if (i > 0 && Character.isDigit(current) && isPrevElementANumber(expressionNodes)) {
                String currentNode = expressionNodes.get(expressionNodes.size() - 1);
                expressionNodes.remove(expressionNodes.size() - 1);
                expressionNodes.add(currentNode + current);
            } else {
                expressionNodes.add(String.valueOf(current));
            }
        }
        this.infixExpressionNodes = expressionNodes;
        return expressionNodes;
    }

    private boolean isPrevElementANumber(final List<String> expressionNodes) {
        if (expressionNodes.get(expressionNodes.size() - 1).length() > 1) {
            return true;
        }
        char current = expressionNodes.get(expressionNodes.size() - 1).charAt(0);
        return Character.isDigit(current);
    }

}
