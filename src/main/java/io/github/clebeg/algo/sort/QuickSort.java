package io.github.clebeg.algo.sort;

import io.github.clebeg.utils.SampleDataUtil;

import java.util.Arrays;

/**
 * 快速排序
 * 1. 选择 pivot （可优化）
 * 2. 通过 pivot 将分成左右两边 （难点）
 * 3. 递归调用左右两边进行快速排序
 * 时间复杂度：平均 nlog(n) 最好 nlog(n) 最坏 n^2
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

    @Override
    public String toString() {
        return "QuickSort{" +
                "costTime=" + costTime +
                ", datasets=" + Arrays.toString(datasets) +
                '}';
    }

    public static void main(String[] args) {
        Integer[] sampleData = SampleDataUtil.randIntArray(10000, 1000, 50000);
        SampleDataUtil.printArray(sampleData);
        QuickSort<Integer> integerQuickSort = new QuickSort<Integer>(sampleData);
        integerQuickSort.sortTimeIt("quick_sort");
        System.out.println(integerQuickSort);
    }
}
