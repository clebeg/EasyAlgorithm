package io.github.clebeg.algo.sort;

import java.util.Comparator;

/**
 * 希尔排序 是插入排序的改进
 * 给定一个分组策略 [1, 2, 4, 8]
 * 1. 先8个一组，分成多行 每列进行插入排序
 * 2. 然后4个一组，分成多行 每列进行插入排序
 * 时间复杂度：平均: O(n^1.3) 最好: O(n) 最坏: O(n^2)
 * 空间复杂度：O(1)
 * In-Place
 * 不稳定
 * 优化点：
 *  分组策略可以继续优化
 * @param <E>
 */
public class ShellSort<E extends Comparable> extends BaseSort<E> {
    public ShellSort(E[] datasets, Comparator<E> comparator) {
        super(datasets, comparator);
    }

    public ShellSort(E[] datasets) {
        super(datasets);
    }

    public void sort() {

    }
}
