package com.ntr.ds.queue;

public interface BlockingQueue<E> {

  void put(E item) throws InterruptedException;

  E take() throws InterruptedException;

   boolean isEmpty();

}
