package com.ntr.parser;

import com.ntr.jvcalc.tokenizer.Token;

import java.util.List;

public interface Parser {

    String toPostfix(List<Token> tokens);
}
