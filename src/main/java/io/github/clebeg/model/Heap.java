package io.github.clebeg.model;

/**
 * interface for heap
 * what's heap
 * 先说大顶堆
 * 1. 完全二叉树 适合用数组来存储 因为是从上到下 从左到右
 * 2. 根节点比子树所有元素都要大 递归
 * 常见误区：以为一层比下层大，不一定，效率最好的堆实现是:斐波那契堆
 * https://en.wikipedia.org/wiki/Heap_%28data_structure%29
 * @param <E>
 */
public interface Heap<E extends Comparable> {
    /**
     * heap size
     * @return heap size
     */
    int size();

    void clear();
    /**
     * heap is empty
     * @return heap empty return true
     */
    boolean isEmpty();

    E getTop();

    E removeTop();

    /**
     * put a element into heap
     */
    void put(E ele);

    /**
     * get top element and put a element into heap
     * @return
     */
    E replaceTop(E ele);
}
