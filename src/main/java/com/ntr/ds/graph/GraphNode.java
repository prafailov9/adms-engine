package com.ntr.ds.graph;

import java.util.List;

public class GraphNode<E> {

    private List<Edge<E>> connectedEdges;
    private E data;

    private GraphNode(final List<Edge<E>> connectedEdges, final E data) {
        this.connectedEdges = connectedEdges;
        this.data = data;
    }

    private GraphNode(final E data) {
        this.data = data;
    }

    public static <E> GraphNode<E> of(final List<Edge<E>> connectedEdges, final E data) {
        return new GraphNode<>(connectedEdges, data);
    }

    public static <E> GraphNode<E> of(final E data) {
        return new GraphNode<>(data);
    }

    public void connectToNode(GraphNode<E> node) {
        var edge = WeightedEdge.of(this, node);
        connectedEdges.add(edge);
    }

    public E getData() {
        return data;
    }

}
