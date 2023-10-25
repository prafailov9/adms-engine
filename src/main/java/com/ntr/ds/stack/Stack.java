package com.ntr.ds.stack;

public interface Stack<E> {

    void push(E elem);

    E pop();

    E peek();

    int size();

    boolean isEmpty();

}
