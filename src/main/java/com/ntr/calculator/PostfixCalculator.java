package com.ntr.calculator;

import com.ntr.calculator.operator.OperatorUtil;
import com.ntr.ds.stack.Stack;
import com.ntr.ds.stack.StackImpl;

public class PostfixCalculator implements Calculator {

    private final Stack<String> valueStack = new StackImpl<>();


    /**
     * Scan the given expression from left to right and do the following for every scanned element.
     * If the element is a number, push it into the stack
     * If the element is an operator, pop operands for the operator from the stack. Evaluate the operator and push the result back to the stack
     * When the expression is ended, the number in the stack is the final answer
     * @param expression
     * @return
     */
    @Override
    public String evaluate(Expression expression) {
        // validate expression
        if (expression == null || expression.getExpression() == null || expression.getExpression().isEmpty()) {
            throw new RuntimeException("Expression cannot be null or empty");
        }
        // scan the expression
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < expression.getExpression().length(); i++) {
            // get the current element
            char current = expression.getExpression().charAt(i);

            if (OperatorUtil.isOperator(current)) {

            } else {
                valueStack.push(String.valueOf(current));
            }
        }
        return null;
    }
}
