package io.github.clebeg.algo.model.graph;

import java.util.Collection;

/**
 * 图接口，抽象出图数据结构应该有的方法
 * 接口默认都是公共函数
 * V：顶点值类型
 * E：边权重类型
 */
public interface Graph<V, E> {
    // 图中顶点数量
    int vertexSize();

    // 图中边数量
    int edgeSize();

    // 添加顶点
    void addVertex(V v);
    void addVertexes(Collection<V> vs);
    // 添加边
    void addEdge(V from, V to, E weight);

    void addEdge(V from, V to);
    // 删除顶点
    void removeVertex(V v);
    // 删除边
    void removeEdge(V from, V to, E weight);

    /**
     * 宽度优先搜索
     * 输入是开始搜索的顶点
     * 通过传入一个 visitor 做用户想做的事情
     * return void
     */
    void bfs(V begin, VertexVisitor<V> vertexVisitor);
    // 深度优先搜索
    void dfs(V begin, VertexVisitor<V> vertexVisitor);
    interface VertexVisitor<V> {
        void visit(V v);
    }
}
