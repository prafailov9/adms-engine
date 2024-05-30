package com.ntr.jvcalc.tokenizer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTokenizerTest {

    @Test
    public void testSingleNumber() {
        ExpressionTokenizer tokenizer = ExpressionTokenizer.of("123");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(1, tokens.size());
        assertEquals(TokenType.NUMBER, tokens.get(0).getTokenType());
        assertEquals("123", tokens.get(0).getValue());
    }

    @Test
    public void testSimpleExpression() {
        ExpressionTokenizer tokenizer = ExpressionTokenizer.of("12 + 24");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(3, tokens.size());
        assertEquals(TokenType.NUMBER, tokens.get(0).getTokenType());
        assertEquals(TokenType.OPERATOR, tokens.get(1).getTokenType());
        assertEquals(TokenType.NUMBER, tokens.get(2).getTokenType());
    }

    @Test
    public void testFunctionCall() {
        ExpressionTokenizer tokenizer = ExpressionTokenizer.of("sin(x)");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(4, tokens.size());
        assertEquals(TokenType.FUNCTION, tokens.get(0).getTokenType());
        assertEquals("sin", tokens.get(0).getValue());
        assertEquals(TokenType.OPEN_PAREN, tokens.get(1).getTokenType());
        assertEquals(TokenType.VARIABLE, tokens.get(2).getTokenType());
        assertEquals("x", tokens.get(2).getValue());
        assertEquals(TokenType.CLOSE_PAREN, tokens.get(3).getTokenType());
    }


    @Test
    public void testInvalidCharacter() {
        ExpressionTokenizer tokenizer = ExpressionTokenizer.of("a + b * $");
        Exception exception = assertThrows(IllegalArgumentException.class, tokenizer::tokenize);
        assertTrue(exception.getMessage().contains("Invalid character: '$'"));
    }

    @Test
    public void testIgnoreWhitespace() {
        ExpressionTokenizer tokenizer = ExpressionTokenizer.of("   a    +    34    ");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(3, tokens.size());
        assertEquals(TokenType.VARIABLE, tokens.get(0).getTokenType());
        assertEquals(TokenType.OPERATOR, tokens.get(1).getTokenType());
        assertEquals(TokenType.NUMBER, tokens.get(2).getTokenType());
    }
}