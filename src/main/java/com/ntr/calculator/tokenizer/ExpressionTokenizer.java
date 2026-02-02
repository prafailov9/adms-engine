package com.ntr.calculator.tokenizer;

import com.ntr.jvcalc.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ExpressionTokenizer implements Tokenizer {

  private String expression;

  public ExpressionTokenizer(final String expression) {
    // remove whitespaces
    this.expression = expression.trim();
  }

  /**
   * for every symbol in the expression, create a token object with its value(ex: '+', '12')
   * and its type(ex: TokenType.OPERATOR, TokenType.NUMBER)
   * ex input: "1+2/41*((12-1)+sin(x))"
   * ex output: list[1, +, 2, /, 41, *, (, (, 12, -, 1, ), +, sin(x)]
   *
   * @return
   */
  @Override
  public List<com.ntr.jvcalc.tokenizer.Token> tokenize() {
    List<Token> tokens = new ArrayList<>();
    int pos = 0;
    while (pos < expression.length()) {
      char current = expression.charAt(pos);
      // for number(single digit/multidigit)
      if (Character.isDigit(current)) {
        Pair<Integer, String> result = extractNumberSequence(pos, expression, Character::isDigit);
        pos = result.v1;
        String value = result.v2;
        tokens.add(Token.of(value, TokenType.NUMBER));
      }
      // for operators
      // for parenthesis
      // for functions
    }
    return null;
  }

  private Pair<Integer, String> extractNumberSequence(int currentPosition, String expression,
      Predicate<Character> condition) {
    int startOfSequence = currentPosition;
    while (currentPosition < expression.length() && condition.test(
        expression.charAt(currentPosition))) {
      currentPosition++;
    }

    return new Pair<>(currentPosition, expression.substring(startOfSequence, currentPosition));
  }

  private static class Pair<T, E> {

    T v1;
    E v2;

    public Pair(T v1, E v2) {
      this.v1 = v1;
      this.v2 = v2;
    }

  }

}
