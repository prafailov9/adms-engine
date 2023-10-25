package com.ntr.cache;

import java.util.Optional;

/***
 * How can we design a data structure that could do operations like reading, sorting (temporal sorting), and deleting elements in constant time?
 *
 * LRU cache and its features:
 * In practice, LRU cache is a kind of Queue — if an element is reaccessed, it goes to the end of the eviction order
 * This queue will have a specific capacity as the cache has a limited size. Whenever a new element is brought in, it is added at the head of the queue. When eviction happens, it happens from the tail of the queue.
 * Hitting data in the cache must be done in constant time, which isn't possible in Queue! But, it is possible with Java's HashMap data structure
 * Removal of the least recently used element must be done in constant time, which means for the implementation of Queue, we'll use DoublyLinkedList instead of SingleLinkedList or an array
 * So, the LRU cache is nothing but a combination of the DoublyLinkedList and the HashMap
 */
public interface Cache<K, V> {

    void put(K key, V value);
    Optional<V> get(K key);
}
