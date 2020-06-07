package io.github.clebeg.algo.sort;

import io.github.clebeg.utils.SampleDataUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 归并排序 重点是两个有序数组如何合并为一个有序数组
 * 1. 将数组分为两半
 * 2. 然后将两半分别进行归并排序
 * 3. 组后将两半排好序的合并为一个排好序的数组
 * 时间复杂度：平均: O(nlog(n)) 最好: O(nlog(n)) 最坏: O(nlog(n))
 * 空间复杂度：O(n)
 * In-Place
 * 稳定
 * 优化点：
 *  TODO: 将递归改成循环
 * @param <E>
 */
public class MergeSort<E extends Comparable> extends BaseSort<E> {
    public MergeSort(E[] datasets, Comparator<E> comparator) {
        super(datasets, comparator);
    }

    public MergeSort(E[] datasets) {
        super(datasets);
    }

    public void sort() {
        mergeSort(0, datasets.length);
    }

    public void mergeSort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (end + begin) >> 1;
        mergeSort(begin, mid);
        mergeSort(mid, end);
        merge(begin, mid, end);
    }

    private void merge(int begin, int mid, int end) {
        E[] tmpArray = (E[]) new Comparable[mid - begin];
        for (int i = begin; i < mid; i++) {
            tmpArray[i - begin] = datasets[i];
        }
        int cur = begin;
        int pl = 0;
        int pr = mid;
        while (pl < (mid - begin)) {
            if (pr < end && cmp(datasets[pr], tmpArray[pl]) < 0) {
                datasets[cur++] = datasets[pr++];
            } else {
                datasets[cur++] = tmpArray[pl++];
            }
        }
    }

    public String show() {
        return "MergeSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                ", datasets=" + Arrays.toString(datasets) +
                '}';
    }

    @Override
    public String toString() {
        return "MergeSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                '}';
    }

    public static void main(String[] args) {
        Integer[] integers = SampleDataUtil.randIntArray(5, 10, 2000000);
        SampleDataUtil.printArray(integers);
        MergeSort mergeSort = new MergeSort(integers);
        mergeSort.sortTimeIt("MergeSort");
        System.out.println(ArrayUtils.isSorted(integers));
        System.out.println(mergeSort.show());
    }
}
