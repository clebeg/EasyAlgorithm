package io.github.clebeg.algo.sort;


import io.github.clebeg.utils.SampleDataUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 插入排序
 * 1. 第一个元素相当于已排序好
 * 2. 然后不断将当前位置数据插入到左边已排序好数组的合适位置中
 * 时间复杂度：平均: O(n^2) 最好: O(n^2) 最坏: O(n^2)
 * 空间复杂度：O(1)
 * In-Place
 * 稳定
 * 优化点：
 *  TODO: 选择合适位置的方式可以用二分查找，减少比较次数
 * @param <E>
 */
public class InsertSort<E extends Comparable> extends BaseSort<E> {
    public InsertSort(E[] datasets, Comparator<E> comparator) {
        super(datasets, comparator);
    }

    public InsertSort(E[] datasets) {
        super(datasets);
    }

    public void sort() {
        for (int i = 1; i < datasets.length; i++) {
            insert(i);
        }
    }

    /**
     * 将 end 所在的元素 插入到 [begin, end) 合适的位置上
     * @param end 有序列表结束位置
     * @return
     */
    private void insert(int end) {
        E pivot = datasets[end];
        while (end > 0 && cmp(datasets[end-1], pivot) > 0) {
            datasets[end] = datasets[end-1];
            end--;
        }
        datasets[end] = pivot;
    }

    public String show() {
        return "InsertSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                ", datasets=" + Arrays.toString(datasets) +
                '}';
    }

    @Override
    public String toString() {
        return "InsertSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                '}';
    }

    public static void main(String[] args) {
        Integer[] integers = SampleDataUtil.randIntArray(20, 5, 30);
        SampleDataUtil.printArray(integers);
        InsertSort insertSort = new InsertSort(integers);
        insertSort.sortTimeIt("InsertSort");
        System.out.println(insertSort.show());
    }
}
