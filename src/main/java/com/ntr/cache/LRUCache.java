package com.ntr.cache;

import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Evicts the least recently used(LRU) object from the queue.
 * When an object is read its node gets detached and is put in front of the queue.
 * When an object is stored in the queue it is put in front.
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> implements Cache<K, V> {

    private final Map<K, CacheObject<K, V>> map;
    private final LinkedList<CacheObject<K, V>> queue;
    private final int capacity;

    public LRUCache(final int capacity) {
        this.capacity = capacity;
        map = new ConcurrentHashMap<>(capacity);
        queue = new LinkedList<>();
    }

    /**
     *
     */
    @Override
    public void put(K key, V value) {
        CacheObject<K, V> obj = CacheObject.create(key, value);
        if (capacity == map.size()) {
            // removing last element from queue and map when on full capacity
            CacheObject<K, V> removed = queue.removeLast();
            map.remove(removed.getKey());
        }
        map.put(key, obj);
        queue.addFirst(obj);
    }

    @Override
    public Optional<V> get(K key) {
        CacheObject<K, V> obj = map.get(key);

        if (obj != null) {
            // add object to front of queue
            queue.remove(obj);
            queue.addFirst(obj);
            return Optional.of(obj.getValue());
        }

        return Optional.empty();
    }

}
