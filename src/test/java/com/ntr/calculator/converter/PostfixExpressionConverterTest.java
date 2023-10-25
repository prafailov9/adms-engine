package com.ntr.calculator.converter;

import com.ntr.calculator.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostfixExpressionConverterTest {

    private Expression expression;
    private ExpressionConverter converter;

    private String expressionString;

    @BeforeEach
    public void setup() {
        expressionString = "1+2/44*(21-(3+2))";
        expression = Expression.of(expressionString);
        converter = new PostfixExpressionConverter();

    }

    @Test
    public void convertToPostfixTest() {
        String actual = "1244/2132+-*+";
        String postfix = converter.convert(expression.getExpression());
        Assertions.assertNotNull(postfix);
        Assertions.assertEquals(postfix, actual);

        System.out.printf("Converted expression %s to postfix %s%n", expressionString, postfix);
    }

}