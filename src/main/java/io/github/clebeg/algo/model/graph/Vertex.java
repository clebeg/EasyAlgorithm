package io.github.clebeg.algo.model.graph;

import java.util.Objects;
import java.util.Set;

public class Vertex<V, E extends Comparable> {
    public Set<Edge<V, E>> inEdges;
    public Set<Edge<V, E>> outEdges;
    public V value;

    public Vertex(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex<V, E> vertex = (Vertex<V, E>) o;
        return getValue().equals(vertex.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
