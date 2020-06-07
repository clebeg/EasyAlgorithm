package io.github.clebeg.algo.sort;

import io.github.clebeg.utils.SampleDataUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 堆排序 其实是选择排序的一种优化
 * 1. 选择堆中最大的元素放到末尾
 * 2. 同时维护堆
 * 3. 初始化的时候要就地生成一个堆，用 shiftUp 的方式
 * 时间复杂度：平均: O(nlog(n)) 最好: O(nlog(n)) 最坏: O(nlog(n))
 * 空间复杂度：O(1)
 * In-Place
 * 稳定
 * @param <E>
 */
public class HeapSort<E extends Comparable> extends BaseSort<E> {
    private int heapSize = 0;
    public HeapSort(E[] datasets, Comparator<E> comparator) {
        super(datasets, comparator);
        inPlaceBuildHeap();
    }

    public HeapSort(E[] datasets) {
        super(datasets);
        inPlaceBuildHeap();
    }

    // 学会原地建堆
    private void inPlaceBuildHeap() {
        heapSize = datasets.length;
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            shiftDown(i);
        }
    }

    public void sort() {
        for (int i = datasets.length - 1; i > 0; i--) {
            // heap size = i+1
            // max value in 0
            swap(0, i);
            heapSize--;
            shiftDown(0);
        }
    }

    private void shiftUp(int index) {
        while (index >= 0) {
            int father = (index - 1) >> 1;
            if (cmp(datasets[father], datasets[index]) < 0) {
                swap(index, father);
                index = father;
            } else {
                break;
            }
        }
    }
    private void shiftDown(int index) {
        while (((index << 1) + 1) < heapSize) {
            int maxChildInd = (index << 1) + 1;
            int leftChildInd = maxChildInd + 1;
            if (leftChildInd < heapSize &&
                    cmp(datasets[maxChildInd], datasets[leftChildInd]) < 0) {
                maxChildInd = leftChildInd;
            }
            if (cmp(datasets[maxChildInd], datasets[index]) > 0) {
                // 如果最大孩子比当前节点值大，交换
                swap(index, maxChildInd);
                index = maxChildInd;
            } else {
                break;
            }
        }
    }


    public String show() {
        return "HeapSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                ", datasets=" + Arrays.toString(datasets) +
                '}';
    }

    @Override
    public String toString() {
        return "HeapSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                '}';
    }

    public static void main(String[] args) {
        Integer[] integers = SampleDataUtil.randIntArray(5, 10, 2000000);
        SampleDataUtil.printArray(integers);
        HeapSort heapSort = new HeapSort(integers);
        heapSort.sortTimeIt("HeapSort");
        System.out.println(ArrayUtils.isSorted(integers));
        System.out.println(heapSort.show());
    }
}
