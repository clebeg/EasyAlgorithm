package io.github.clebeg.algo.model.graph;

import java.util.Arrays;
import java.util.List;


public class GraphTest {
    public static void main(String[] args) {
        List<Edge<Integer, Integer>> edges = Arrays.asList(
            new Edge(1, 2, 1),
            new Edge(1, 8, 1),
            new Edge(2, 3, 1),
            new Edge(2, 5, 1),
            new Edge(3, 4, 1),
            new Edge(5, 4, 1),
            new Edge(8, 6, 1),
            new Edge(8, 9, 1),
            new Edge(6, 7, 1),
            new Edge(6, 4, 1)
        );
        ListGraph<Integer, Integer> veListGraph = new ListGraph(edges, false);
        System.out.println("BFS......");
        veListGraph.bfs(1, (vertex) -> System.out.print(vertex + "\t"));
        System.out.println("\nDFS......");
        veListGraph.dfs(1, (vertex) -> System.out.print(vertex + "\t"));
        System.out.println();

    }
}
