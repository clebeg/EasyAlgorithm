package io.github.clebeg.algo.sort;

import io.github.clebeg.utils.SampleDataUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
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
 * 分组策略可以继续优化
 *
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
        for (int groupNum = datasets.length >> 1; groupNum > 2; groupNum >>= 1) {
            sortByGroup(groupNum);
        }
        sortByGroup(1);
    }

    private void sortByGroup(int groupNum) {
        for (int i = 0; i < groupNum; i++) {
            for (int j = groupNum; j < datasets.length; j+=groupNum) {
                insert(j, groupNum);
            }
        }
    }

    /**
     * 将end所在的元素 按照步长 groupNum 插入排序
     * @param end 准备插入的元素下标
     * @param groupNum 步长
     */
    private void insert(int end, int groupNum) {
        E pivot = datasets[end];
        while ((end - groupNum) > 0 && cmp(datasets[end - groupNum], pivot) > 0) {
            datasets[end] = datasets[end - groupNum];
            end -= groupNum;
        }
        // 最后记得强行插入
        datasets[end] = pivot;
    }

    public String show() {
        return "ShellSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                ", datasets=" + Arrays.toString(datasets) +
                '}';
    }

    @Override
    public String toString() {
        return "ShellSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                '}';
    }

    public static void main(String[] args) {
        Integer[] integers = SampleDataUtil.randIntArray(5, 5, 30);
        SampleDataUtil.printArray(integers);
        ShellSort shellSort = new ShellSort(integers);
        shellSort.sortTimeIt("QuickSort");
        System.out.println(shellSort.show());
    }
}
