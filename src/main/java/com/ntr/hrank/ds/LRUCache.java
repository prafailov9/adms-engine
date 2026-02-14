package com.ntr.hrank.ds;

public interface LRUCache<K, V> {

  void put(K key, V value);

  V get(K key); 
}
