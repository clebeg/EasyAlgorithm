package io.github.clebeg.algo.model.graph;

import io.github.clebeg.algo.model.UnionFind;

import java.util.*;

class ListGraph<V, E extends Comparable> implements Graph<V, E> {
    boolean hasDirection = false;
    HashMap<V, Vertex<V, E>> vertexHM = new HashMap<>();
    Set<Vertex<V, E>> vertexSet = new HashSet<>();
    Set<Edge<V, E>> edgeSet = new HashSet<>();

    public int vertexSize() {
        return vertexSet.size();
    }

    public int edgeSize() {
        return edgeSet.size();
    }

    public void addVertex(V v) {
        if (vertexHM.containsKey(v)) return;
        Vertex<V, E> vertex = new Vertex<>(v);
        vertexHM.put(v, vertex);
        vertexSet.add(vertex);
    }

    public void addVertexes(Collection<V> vs) {
        vs.forEach(this::addVertex);
    }

    public void addEdge(V from, V to, E weight) {
        Vertex<V, E> fromVertex = vertexHM.getOrDefault(from, new Vertex(from));
        Vertex<V, E> toVertex = vertexHM.getOrDefault(to, new Vertex(to));
        if (toVertex.inEdges == null) {
            toVertex.inEdges = new HashSet<>();
        }
        if (fromVertex.outEdges == null) {
            fromVertex.outEdges = new HashSet<>();
        }
        Edge<V, E> newEdge = new Edge(from, to, weight);
        fromVertex.outEdges.add(newEdge);
        toVertex.inEdges.add(newEdge);
        vertexHM.put(from, fromVertex);
        vertexHM.put(to, toVertex);
        edgeSet.add(newEdge);
    }

    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    /**
     * 删除一个顶点
     * 从顶点集合中移除 从顶点Map中移除
     * 需要删除从它出发的边和指向它的边
     * @param v 图顶点
     */
    public void removeVertex(V v) {
        if (!vertexHM.containsKey(v)) return;
        Vertex<V, E> vertex = vertexHM.remove(v);
        vertexSet.remove(vertex);
        // 从集合中一边遍历一边删除 java 中使用迭代器
        edgeSet.removeIf(edge -> edge.from.equals(v) || edge.to.equals(v));
    }

    public void removeEdge(V from, V to, E weight) {
        Iterator<Edge<V, E>> it = edgeSet.iterator();
        while (it.hasNext()) {
            Edge edge = it.next();
            if (edge.from.equals(from) &&
                    edge.to.equals(to) &&
                    edge.weight.equals(weight)) {
                Vertex<V, E> fromVertex = vertexHM.get(edge.from);
                fromVertex.outEdges.remove(edge.to);
                Vertex<V, E> toVertex = vertexHM.get(edge.to);
                toVertex.inEdges.remove(edge.from);
                it.remove();
            }
        }
    }

    /**
     * 广度优先搜索
     * 实现思路：通过队列来实现
     * 初始化：初始化记录已访问过节点的Set，初始化队列，将起始节点放入队列
     * 循环直到队列为空：
     *    队顶元素出队
     *        如果此顶点访问过，继续循环
     *        否则访问此元素，并且标记为已访问，然后将以此节点出发的边指向的所有顶点加入队列，继续循环
     * Tips：在 java 中可以利用 LinkedList 来实现 堆栈和队列
     * 队列先进先出 对应 List 的 offer，poll  方法组后
     * 堆栈后进先出 对应 List 的 addFirst，poll 方法组后
     * 而 peek 只是查看一下 head 元素
     * @param begin
     * @param vertexVisitor
     */
    public void bfs(V begin, VertexVisitor<V> vertexVisitor) {
        HashSet<V> visited = new HashSet<>();
        if (!vertexHM.containsKey(begin)) return;
        LinkedList<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(vertexHM.get(begin));
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            // 如果观察过，就直接继续观察其他顶点
            if (visited.contains(vertex.value)) continue;
            vertexVisitor.visit(vertex.value);
            visited.add(vertex.value);
            vertex.outEdges.forEach(edge -> {
                V toEdge = edge.to;
                queue.offer(vertexHM.get(toEdge));
            });
        }
    }

    /**
     * 深度优先搜索
     * 实现思路：利用堆栈来实现
     * 初始化：
     *   初始化记录已访问过节点的Set，初始化堆栈，将起始节点放入堆栈
     *   查看起始元素，并将其放入已访问Set中
     * 循环直到堆栈为空：
     *   取出堆栈顶部元素，但不是移除
     *   判断以堆栈顶部元素出发的所有边指向的顶点是否还有未访问的顶点：
     *     如果有：选择其中一个未访问的顶点，访问，并标记为已访问，将其加入堆栈
     *     如果没有：将此顶点移出堆栈
     * @param begin
     * @param vertexVisitor
     */
    public void dfs(V begin, VertexVisitor<V> vertexVisitor) {
        HashSet<V> visited = new HashSet<>();
        if (!vertexHM.containsKey(begin)) return;
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.addFirst(vertexHM.get(begin));
        vertexVisitor.visit(begin);
        visited.add(begin);

        while (!stack.isEmpty()) {
            Vertex<V, E> top = stack.peek();
            boolean flag = true;
            for (Edge<V, E> outEdge : top.outEdges) {
                if (visited.contains(outEdge.to)) continue;
                vertexVisitor.visit(outEdge.to);
                visited.add(outEdge.to);
                stack.addFirst(vertexHM.get(outEdge.to));
                flag = false;
                break;
            }
            if (flag) stack.poll();
        }
    }

    /**
     * 最小生成树：minimum spanning tree
     * 主要两种算法：prim kruskal
     * 具备最小生成树的图必须满足：无向联通图
     * @return 组成最小生成树的边
     */
    @Override
    public Set<Edge<V, E>> mst(String method) {
        if (this.hasDirection) {
            throw new UnsupportedOperationException("Only No direction Has Minimum Spanning Tree.");
        }
        if (method.equals("prim")) {
            return prim();
        }
        if (method.equals("kruskal")){
            return kruskal();
        }
        throw new UnsupportedOperationException("minimum spanning tree method only support: prim, kruskal");
    }

    /**
     * 最小生成树：minimum spanning tree
     * kruskal 算法，将边按权重从小到大排序，权重小的一定属于最小生成树，但是必须满足一定的条件，就是此边是属于两个集合
     * 算法原理：
     *  初始化：将所有的顶点单独成立一个集合，将所有边放入到最小堆中，将最终组成最小生成树的所有边组成的Set初始化为空Set
     * 循环直到（最小生成树的边等于【顶点数量-1】 或者 最小堆为空）：
     *  从最小堆中得到权重最小的分割边
     *  判断此边连接的两个顶点是不是属于一个集合：
     *      如果是：继续循环
     *      如果不是：
     *          将此边加入最小生成树的边中
     *          并且将此边连接的两个顶点的集合组成一个大集合
     * 此算法需要用到并查集
     * Todo：需要一个支持泛型的并查集
     * @return 组成最小生成树的边 如果不是无向联通图就抛出异常
     */
    private Set<Edge<V,E>> kruskal() {
        HashSet<Edge<V, E>> mstEdges = new HashSet<>();
        PriorityQueue<Edge<V, E>> queue = new PriorityQueue<>(edgeSet);
        // 需要一个通用的并查集
        return null;
    }

    /**
     * 最小生成树：minimum spanning tree
     * prim 算法，本质是两个子图直接的所有链接边，最小的边一定属于最小生成树的边，于是可以将一个顶点慢慢扩散到所有顶点
     * 算法原理：
     *  初始化：将最终组成最小生成树的所有边组成的Set初始化为空Set、初始化访问过的顶点Set、最小堆存放所有的分割边
     *  初始化顶点、将顶点对应的所有outEdges加入最小堆中、将初始化顶点加入访问过的顶点集合中
     * 循环直到（最小生成树的边等于【顶点数量-1】 或者 最小堆为空）：
     *  从最小堆中得到权重最小的分割边
     *  判断此边的to顶点是否访问过：
     *      如果是：
     *          将此边加入最小生成树的边中，将此边的to顶点加入访问过的集合中
     *          将此边对应的to顶点的outEdges边加入最小堆中
     *      否则继续循环
     * @return 组成最小生成树的边 如果不是无向联通图就抛出异常
     */
    private Set<Edge<V, E>> prim() {
        Iterator<Vertex<V, E>> it = vertexSet.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Vertex<V, E> begin = it.next();
        PriorityQueue<Edge<V, E>> queue = new PriorityQueue<>(begin.outEdges);
        HashSet<V> visited = new HashSet<>();
        HashSet<Edge<V, E>> mstEdges = new HashSet<>();
        visited.add(begin.value);

        while (!queue.isEmpty() && visited.size() < vertexSet.size()) {
            Edge<V, E> curEdge = queue.poll();
            if (visited.contains(curEdge.to)) continue;
            visited.add(curEdge.to);
            mstEdges.add(curEdge);
            queue.addAll(vertexHM.get(curEdge.to).outEdges);
        }
        if (visited.size() < vertexSet.size()) {
            throw new UnsupportedOperationException("Only Connect Graph Has Minimum Spanning Tree.");
        } else {
            return mstEdges;
        }
    }


    public ListGraph(Collection<Edge<V, E>> edges, boolean hasDirection) {
        this.hasDirection = hasDirection;
        if (hasDirection) {
            edges.forEach(edge -> addEdge(edge.from, edge.to, edge.weight));
        } else {
            edges.forEach(edge -> addEdge(edge.from, edge.to, edge.weight));
            edges.forEach(edge -> addEdge(edge.to, edge.from, edge.weight));
        }
    }

}
