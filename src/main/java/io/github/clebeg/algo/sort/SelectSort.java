package io.github.clebeg.algo.sort;


import io.github.clebeg.utils.SampleDataUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 选择排序
 * 1. 每次选择一个最大的元素放到后面
 * 时间复杂度：平均: O(n^2) 最好: O(n^2) 最坏: O(n^2)
 * 空间复杂度：O(1)
 * In-Place
 * 稳定
 * 优化点：
 *  选择的时候用最大堆 堆排序
 * @param <E>
 */
public class SelectSort<E extends Comparable> extends BaseSort<E> {
    public SelectSort(E[] datasets, Comparator<E> comparator) {
        super(datasets, comparator);
    }

    public SelectSort(E[] datasets) {
        super(datasets);
    }

    public void sort() {
        // 每次最大的元素
        for (int i = datasets.length - 1; i > 0; i--) {
            int maxInd = 0;
            for (int j = 1; j <= i; j++) {
                if (cmp(datasets[j], datasets[maxInd]) > 0) {
                    maxInd = j;
                }
            }
            swap(maxInd, i);
        }
    }

    public String show() {
        return "SelectSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                ", datasets=" + Arrays.toString(datasets) +
                '}';
    }

    @Override
    public String toString() {
        return "SelectSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                '}';
    }

    public static void main(String[] args) {
        Integer[] integers = SampleDataUtil.randIntArray(10, 5, 500);
        SampleDataUtil.printArray(integers);
        SelectSort selectSort = new SelectSort(integers);
        selectSort.sortTimeIt("SelectSort");
        System.out.println(ArrayUtils.isSorted(integers));
        System.out.println(selectSort.show());
    }
}
