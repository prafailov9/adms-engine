package com.ntr.jvcalc.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ExpressionTokenizer implements Tokenizer {

    private final String expression;

    private ExpressionTokenizer(final String expression) {
        this.expression = expression.replaceAll("\\s+", ""); // remove all whitespaces
    }

    public static ExpressionTokenizer of(final String expression) {
        return new ExpressionTokenizer(expression);
    }

    @Override
    public List<Token> tokenize() {
        int pos = 0;
        List<Token> tokens = new ArrayList<>();

        while (pos < expression.length()) {
            char current = expression.charAt(pos);
            // check for a whole number
            if (Character.isDigit(current)) {
                Sequence sequence = extractSequence(pos, Character::isDigit);
                tokens.add(Token.of(sequence.text, TokenType.NUMBER));
                pos = sequence.position;
                // check if it's a function or a variable
            } else if (Character.isLetter(current)) {
                Sequence sequence = extractSequence(pos, Character::isLetter);
                pos = sequence.position;
                if (expression.charAt(pos) == '(') {
                    tokens.add(Token.of(sequence.text, TokenType.FUNCTION));
                } else {
                    tokens.add(Token.of(sequence.text, TokenType.VARIABLE));
                }
            } else if (isOperator(current)) {
                tokens.add(Token.of(Character.toString(current), TokenType.OPERATOR));
                pos++;
            } else if (isParenthesis(current)) {
                tokens.add(Token.of(Character.toString(current),
                        current == '(' ? TokenType.OPEN_PAREN : TokenType.CLOSE_PAREN));
                pos++;
            } else if (current == ' ') {
                pos++;
            } else {
                throw new IllegalArgumentException("Invalid character: '" + current + "'");
            }
        }
        return tokens;
    }

    private Sequence extractSequence(int pos, Predicate<Character> condition) {
        int startPos = pos;
        StringBuilder sequence = new StringBuilder();
        while (startPos < expression.length() && condition.test(expression.charAt(startPos))) {
            sequence.append(expression.charAt(startPos));
            startPos++;
        }
        return new Sequence(sequence.toString(), startPos);
    }

    private boolean isOperator(final char op) {
        return op == '+' || op == '-' || op == '*' || op == '/' || op == '^';
    }

    private boolean isParenthesis(final char op) {
        return op == '(' || op == ')';
    }

    private static class Sequence {
        String text;
        int position;

        Sequence(String text, int position) {
            this.text = text;
            this.position = position;
        }
    }

}
