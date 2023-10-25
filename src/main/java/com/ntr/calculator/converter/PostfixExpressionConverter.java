package com.ntr.calculator.converter;

import com.ntr.calculator.operator.Operator;
import com.ntr.calculator.operator.OperatorHolder;
import com.ntr.ds.stack.Stack;
import com.ntr.ds.stack.StackImpl;

import static com.ntr.calculator.operator.OperatorUtil.isOperator;

public class PostfixExpressionConverter implements ExpressionConverter {


    Stack<Character> operatorStack = new StackImpl<>();

    @Override
    public String convert(String infix) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < infix.length(); i++) {
            char current = infix.charAt(i);

            // if operand then append to the output
            if (Character.isDigit(current) || Character.isLetter(current)) {
                output.append(current);
            }
            if (isOperator(current)) {
                Operator currentOperator = OperatorHolder.getOperator(current);
                if (!operatorStack.isEmpty()) {
                    while (isOperator(operatorStack.peek()) &&
                            OperatorHolder.getOperator(operatorStack.peek()).getPrecedence() >= currentOperator.getPrecedence()) {
                        Operator top = OperatorHolder.getOperator(operatorStack.pop());
                        output.append(top.getSymbol());
                    }
                }
                operatorStack.push(currentOperator.getSymbol());
            }

            if (current == '(') {
                operatorStack.push(current);
            }

            if (current == ')') {
                while (operatorStack.peek() != '(') {
                    output.append(operatorStack.pop());
                }
                operatorStack.pop();
            }
        }
        while (!operatorStack.isEmpty()) {
            output.append(operatorStack.pop());
        }

        return output.toString();
    }


}
