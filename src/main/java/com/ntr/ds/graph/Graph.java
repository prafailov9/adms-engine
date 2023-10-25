package com.ntr.ds.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph<E> {

    private List<GraphNode<E>> nodes = new ArrayList<>();

    public void insertNode(E data, List<GraphNode<E>> nodes) {
       var node = GraphNode.of(data);

        nodes.forEach(n -> {
            // connecting both nodes to each other
            node.connectToNode(n);
            n.connectToNode(node);
        });

        nodes.add(node);

    }
}
