package com.ntr.concurrent.usethreads.tproblems.memoizer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class DefaultMemoizer<K, V> implements Memoizer<K, V> {

  private final Map<K, V> cache = new HashMap<>();
  private final Object lock = new Object();
  private int accessing = 0;

  private final Set<K> keyCache = new HashSet<>();

  @Override
  public V getOrCompute(K key, Callable<V> compute) throws Exception {
    // tell the cache what value you are asking for
    synchronized (lock) {
      accessing++;
      while (cache.containsKey(key)) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
      // only one must run the computation
      if (!cache.containsKey(key)) {
        V value = compute.call();
        cache.put(key, value);
        return value;
      }
      lock.notifyAll();
    }
    return cache.get(key);
  }

  private V doCompute(K key, Callable<V> compute) throws Exception {
    V value = compute.call();
    cache.put(key, value);
    return value;
  }

  private final class Entry {

  }

}
