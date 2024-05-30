package com.ntr.hrank;

import java.util.*;

public class Graph<N> {

    private final Map<N, List<N>> cache = new HashMap<>();

    public Graph() {

    }

    public void addNode(N node, List<N> connections) {
        if (!cache.containsKey(node)) {
            // making sure the input connections don't have duplicate nodes
            cache.put(node, new ArrayList<>(new HashSet<>(connections)));
        } else {
            doUpdate(node, connections);
        }
    }

    public void updateNode(N node, List<N> connections) {
        enforceExist(node);
        doUpdate(node, connections);
    }

    public void deleteNode(N node) {
        enforceExist(node);
        // remove node entry
        List<N> edges = cache.remove(node);
        // remove node from each node list it was connected to
        edges.forEach(current -> cache.get(current).remove(node));
    }

    public List<N> getEdges(N node) {
        return cache.getOrDefault(node, Collections.emptyList());
    }

    private void doUpdate(N node, List<N> connections) {
        Set<N> uniqueConnections = new HashSet<>(connections);
        uniqueConnections.forEach(current -> addEdge(current, node));
        cache.put(node, new ArrayList<>(uniqueConnections));
    }

    private void addEdge(N from, N to) {
        cache.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    private void enforceExist(N node) {
        if (!cache.containsKey(node)) {
            throw new RuntimeException("node doesn't exist");
        }
    }

}
