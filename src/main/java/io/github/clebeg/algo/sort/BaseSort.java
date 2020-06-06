package io.github.clebeg.algo.sort;

import java.util.Arrays;
import java.util.Comparator;

abstract class BaseSort<E extends Comparable> implements ISort {
    protected long costTime = 0;
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
        System.out.println(algoName + ": CostTime(ms) = " + costTime);
    }
    abstract public void sort();

    protected void swap(int from, int target) {
        E fromEle = datasets[from];
        datasets[from] = datasets[target];
        datasets[target] = fromEle;
    }

    protected int cmp(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return e1.compareTo(e2);
    }

    @Override
    public String toString() {
        return "BaseSort{" +
                "datasets=" + Arrays.toString(datasets) +
                '}';
    }
}
