package com.ntr.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ExpressionTest {

    private Expression expression;

    @BeforeEach
    public void setup() {
        expression = Expression.of("(15609+23)/8812");

    }

    @Test
    public void buildInfixExpressionNodes() {
        List<String> expNodes = expression.buildInfixExpressionNodes();
        Assertions.assertNotNull(expNodes);
        Assertions.assertEquals("15609", expNodes.get(1));
        Assertions.assertEquals("8812", expNodes.get(expNodes.size() - 1));
        System.out.println(expNodes);
    }

}