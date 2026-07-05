package com.ntr.cache;

public interface LinkedList<E> {

  void addFirst(E elem);

  void addLast(E elem);

  E remove(E elem); // O(1) arbitrary removal

  E removeFirst();

  E removeLast();

  E peekLast(); // used for eviction

  boolean isEmpty();

  int size();

}
