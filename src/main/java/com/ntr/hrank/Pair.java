package com.ntr.hrank;

public class Pair<F, S> {

    F first;
    S second;

    private Pair() {

    }

    private Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public static <F, S> Pair<F, S> of(F first, S second) {
        return new Pair<>(first, second);
    }

}
