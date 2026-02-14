package com.ntr.hrank.ds;

public final class CacheObject<K, V> {

  private final K key;
  private final V value;

  private CacheObject(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public static <K, V> CacheObject<K, V> of(K key, V value) {
    return new CacheObject<>(key, value);
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }
}
