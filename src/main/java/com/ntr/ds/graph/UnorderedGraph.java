package com.ntr.ds.graph;

import java.util.*;

/**
 * A | B, C
 * B | A, C
 * C | A, B
 */
public class UnorderedGraph<E> {

    private final Map<E, List<E>> adjacencyList;

    public UnorderedGraph() {
        adjacencyList = new HashMap<>();
    }

    public void add(E elem, List<E> nodes) {
        if (adjacencyList.containsKey(elem)) {
            List<E> currentNodes = adjacencyList.get(elem);
            Set<E> uniqueNodes = new HashSet<>(nodes);
            uniqueNodes.addAll(currentNodes);
            adjacencyList.put(elem, uniqueNodes.stream().toList());
        } else {
            adjacencyList.put(elem, nodes);
        }
    }

    public E remove(E elem) {
        return elem;
    }

}
