package com.ntr.ds.queue;

public interface Queue<E> extends Iterable<E> {

    void offer(E data);

    E poll();

    E peek();

    boolean isEmpty();

    int size();

}
