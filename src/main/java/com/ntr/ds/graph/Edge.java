package com.ntr.ds.graph;

/**
 * Connects two nodes to each other
 */
public abstract class Edge<E> {

    private final GraphNode<E> first;
    private final GraphNode<E> second;


    public Edge(final GraphNode<E> first, final GraphNode<E> second) {
        this.first = first;
        this.second = second;
    }


    public GraphNode<E> getFirst() {
        return first;
    }

    public GraphNode<E> getSecond() {
        return second;
    }


}
