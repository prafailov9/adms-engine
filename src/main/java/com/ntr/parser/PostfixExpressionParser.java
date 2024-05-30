package com.ntr.parser;


import com.ntr.calculator.operator.Operator;
import com.ntr.ds.stack.Stack;
import com.ntr.ds.stack.StackImpl;
import com.ntr.jvcalc.tokenizer.Precedence;
import com.ntr.jvcalc.tokenizer.Token;
import com.ntr.jvcalc.tokenizer.TokenType;
import com.ntr.jvcalc.tokenizer.Tokenizer;

import java.util.List;
import java.util.Queue;

public class PostfixExpressionParser implements Parser {

    private final Tokenizer tokenizer;

    private PostfixExpressionParser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public static PostfixExpressionParser of(final Tokenizer tokenizer) {
        return new PostfixExpressionParser(tokenizer);
    }

    @Override
    public String toPostfix(List<Token> tokens) {
        StringBuilder output = new StringBuilder();
        Stack<Token> operatorStack = new StackImpl<>();
        for (Token token : tokenizer.tokenize()) {
            if (token.getTokenType().equals(TokenType.NUMBER) || token.getTokenType().equals(TokenType.VARIABLE)) {
                output.append(token.getValue());
            } else if (token.getTokenType().equals(TokenType.OPERATOR)) {
                parseOperatorTokens(operatorStack, output, token);
            } else if (token.getTokenType().equals(TokenType.FUNCTION) || token.getTokenType().equals(TokenType.OPEN_PAREN)) {
                operatorStack.push(token);
            } else if (token.getTokenType().equals(TokenType.CLOSE_PAREN)) {
                parseParenthesisTokens(operatorStack, output);
            } else {
                throw new IllegalArgumentException("Unexpected token type: " + token.getTokenType());
            }
        }

        while (!operatorStack.isEmpty()) {
            if (operatorStack.peek().getTokenType().equals(TokenType.OPEN_PAREN)
                    || operatorStack.peek().getTokenType().equals(TokenType.CLOSE_PAREN)) {
                throw new RuntimeException("Mismatched parenthesis!");
            }
            output.append(operatorStack.pop());
        }
        return output.toString();
    }

    private void parseOperatorTokens(Stack<Token> operatorStack, StringBuilder output, Token token) {
        while (!operatorStack.isEmpty() && operatorStack.peek().getTokenType().equals(TokenType.OPERATOR)
                && Precedence.valueOf(token.getValue()).getVal() <= Precedence.valueOf(operatorStack.peek().getValue()).getVal()) {
            output.append(operatorStack.pop());
        }
        operatorStack.push(token);
    }

    private void parseParenthesisTokens(Stack<Token> operatorStack, StringBuilder output) {
        // while the current operator at the top of the op_stack is not
        // an open parenthesis - append the operator to the output and remove it from the op_stack
        while (!operatorStack.isEmpty() && !operatorStack.peek().getTokenType().equals(TokenType.OPEN_PAREN)) {
            output.append(operatorStack.pop());
        }
        if (operatorStack.isEmpty()) {
            throw new RuntimeException("Mismatched parenthesis!");
        }
        operatorStack.pop();
        // after appending the parenthesis' subexpression to the output
        // check if those parenthesis where part of a function
        // then add the function to the output
        if (operatorStack.peek().getTokenType().equals(TokenType.FUNCTION)) {
            output.append(operatorStack.pop());
        }
    }

}
