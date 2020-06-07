package io.github.clebeg.algo.sort;


import io.github.clebeg.utils.SampleDataUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 冒泡排序
 * 1. 每次将一个最大的元素往上浮
 * 时间复杂度：平均: O(n^2) 最好: O(n^2) 最坏: O(n^2)
 * 空间复杂度：O(1)
 * In-Place
 * 稳定
 * 优化点：
 *  2,1,0,5,6,7
 *  lsi=0,1
 *  记住最后一次交换的位置(交换前一个位置），下一次可以不用对后面已经排序好的进行再次冒泡
 * @param <E>
 */
public class BubbleSort<E extends Comparable> extends BaseSort<E> {
    public BubbleSort(E[] datasets, Comparator<E> comparator) {
        super(datasets, comparator);
    }

    public BubbleSort(E[] datasets) {
        super(datasets);
    }

    public void sort() {
        // 每次往上冒泡一个最大的元素
        int j = datasets.length - 1;
        while (j > 0) {
            int lastSwapInd = 0;
            for (int i = 1; i <= j; i++) {
                // 如果前一个元素大，交换
                if (cmp(datasets[i], datasets[i-1]) < 0) {
                    swap(i-1, i);
                    lastSwapInd = i-1;
                }
            }
            j = lastSwapInd;
        }

    }

    public String show() {
        return "BubbleSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                ", datasets=" + Arrays.toString(datasets) +
                '}';
    }

    @Override
    public String toString() {
        return "BubbleSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                '}';
    }

    public static void main(String[] args) {
        Integer[] integers = SampleDataUtil.randIntArray(10, 5, 500);
        SampleDataUtil.printArray(integers);
        BubbleSort bubbleSort = new BubbleSort(integers);
        bubbleSort.sortTimeIt("BubbleSort");
        System.out.println(ArrayUtils.isSorted(integers));
        System.out.println(bubbleSort.show());
    }
}
