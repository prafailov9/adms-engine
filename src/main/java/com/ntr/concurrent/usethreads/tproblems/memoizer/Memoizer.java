package com.ntr.concurrent.usethreads.tproblems.memoizer;

import java.util.concurrent.Callable;

public interface Memoizer<K, V> {
    V getOrCompute(K key, Callable<V> compute) throws Exception;
}
