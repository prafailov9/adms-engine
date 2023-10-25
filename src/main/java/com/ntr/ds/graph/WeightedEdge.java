package com.ntr.ds.graph;

public class WeightedEdge<E> extends Edge<E> {



    private WeightedEdge(final GraphNode<E> first, final GraphNode<E> second) {
        super(first, second);
    }

    public static <E> WeightedEdge<E> of(final GraphNode<E> first, final GraphNode<E> second) {
        return new WeightedEdge<>(first, second);
     }

}
