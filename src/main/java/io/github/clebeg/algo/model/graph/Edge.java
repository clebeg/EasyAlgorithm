package io.github.clebeg.algo.model.graph;

import java.util.Objects;

/**
 * 抽象出图的边
 * @param <V>
 * @param <E>
 */
public class Edge<V, E extends Comparable> implements Comparable<Edge<V, E>>{
    protected V from;
    protected V to;
    protected E weight;

    public Edge(V from, V to, E weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?, ?> edge = (Edge<?, ?>) o;
        return from.equals(edge.from) &&
                to.equals(edge.to) &&
                weight.equals(edge.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, weight);
    }

    @Override
    public int compareTo(Edge<V, E> o) {
        return this.weight.compareTo(o.weight);
    }
}
