package io.github.clebeg.algo.sort;

import java.util.Arrays;
import java.util.Comparator;

abstract class BaseSort<E extends Comparable> implements ISort {
    protected long costTime = 0;
    // 交换次数
    protected int swapTimes = 0;
    // 比较次数
    protected int compareTimes = 0;
    protected E[] datasets = null;
    protected Comparator<E> comparator = null;

    public BaseSort(E[] datasets, Comparator<E> comparator) {
        this.datasets = datasets;
        this.comparator = comparator;
    }

    public BaseSort(E[] datasets) {
        this.datasets = datasets;
    }

    /**
     * 调用排序算法 并且打印耗时
     * @param algoName 算法名称
     */
    public void sortTimeIt(String algoName) {
        long begin = System.currentTimeMillis();
        sort();
        long end = System.currentTimeMillis();
        costTime = end - begin;
    }
    abstract public void sort();

    protected void swap(int from, int target) {
        swapTimes++;
        E fromEle = datasets[from];
        datasets[from] = datasets[target];
        datasets[target] = fromEle;
    }

    protected int cmp(E e1, E e2) {
        compareTimes++;
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return e1.compareTo(e2);
    }

}
