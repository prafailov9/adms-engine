package com.ntr.ds.queue;

public interface Queue<E> extends Iterable<E> {

    void offer(E data);

    E take();

    E peek();

    boolean isEmpty();

    int size();

}
