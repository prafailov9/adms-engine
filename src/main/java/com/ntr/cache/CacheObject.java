package com.ntr.cache;

public class CacheObject<K, V> {
    private K key;
    private V value;
    private int hitCount = 0;
    private CacheObject<K, V> next;
    private CacheObject<K, V> prev;

    private CacheObject(K key, V value) {
        this.value = value;
        this.key = key;
    }

    public static <K, V> CacheObject<K, V> create(final K key, final V value) {
        CacheObject<K, V> obj = new CacheObject<>(key, value);
        obj.next = null;
        obj.prev = null;

        return obj;
    }

    public void incrementHitCount() {
        this.hitCount++;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
