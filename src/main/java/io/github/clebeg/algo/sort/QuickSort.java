package io.github.clebeg.algo.sort;

import io.github.clebeg.utils.SampleDataUtil;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

/**
 * 快速排序 可以说是排序算法中最好的
 * 1. 选择 pivot （可优化）
 * 2. 通过 pivot 将分成左右两边 （难点）
 * 3. 递归调用左右两边进行快速排序 （可优化）
 * 时间复杂度：平均: O(nlog(n)) 最好: O(nlog(n)) 最坏: n^2
 * 空间复杂度：O(nlog(n))
 * In-Place
 * 不稳定
 * 优化点：
 *  随机选取 pivot
 *  TODO: 当数据量少于一定量时采用插入排序
 * @param <E>
 */
public class QuickSort<E extends Comparable> extends BaseSort<E> {
    public QuickSort(E[] sampleData) {
        super(sampleData);
    }

    public void sort() {
        quickSort(0, datasets.length);
    }

    /**
     * 对待排序的数组 [begin, end) 范围内的数据进行排序
     * @param begin
     * @param end
     */
    private void quickSort(int begin, int end) {
        if ((end - begin) < 2) {
            return;
        }
        int randIdx = RandomUtils.nextInt(begin, end);
        swap(begin, randIdx);
        E pivot = datasets[begin];
        int mid = findPivotInd(pivot, begin, end);

        quickSort(begin, mid);
        quickSort(mid+1, end);
    }

    private int findPivotInd(E pivot, int begin, int end) {
        end--;
        while (begin < end) {
            while (begin < end) {
                if (cmp(datasets[end], pivot) > 0) {
                    end--;
                } else {
                    datasets[begin] = datasets[end];
                    begin++;
                    break;
                }
            }

            while (begin < end) {
                if (cmp(datasets[begin], pivot) < 0) {
                    begin++;
                } else {
                    datasets[end] = datasets[begin];
                    end--;
                    break;
                }
            }
        }
        datasets[begin] = pivot;
        return begin;
    }

    public String show() {
        return "QuickSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                ", datasets=" + Arrays.toString(datasets) +
                '}';
    }

    @Override
    public String toString() {
        return "QuickSort{" +
                "costTime=" + costTime +
                "(ms), swapTimes=" + swapTimes +
                ", compareTimes=" + compareTimes +
                '}';
    }


    public static void main(String[] args) {
        Integer[] integers = SampleDataUtil.randIntArray(20, 5, 30);
        SampleDataUtil.printArray(integers);
        QuickSort quickSort = new QuickSort(integers);
        quickSort.sortTimeIt("QuickSort");
        System.out.println(quickSort.show());
    }
}
