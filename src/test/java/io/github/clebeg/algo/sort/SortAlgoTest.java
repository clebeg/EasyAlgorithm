package io.github.clebeg.algo.sort;

import io.github.clebeg.algo.sort.BaseSort;
import io.github.clebeg.algo.sort.InsertSort;
import io.github.clebeg.algo.sort.QuickSort;
import io.github.clebeg.algo.sort.ShellSort;
import io.github.clebeg.utils.SampleDataUtil;
import org.apache.commons.lang3.ArrayUtils;

public class SortAlgoTest {
    public static void main(String[] args) {
        Integer[] datasets = SampleDataUtil.randIntArray(100000, 0, 200000);
        BaseSort[] baseSorts = {
                new MergeSort(ArrayUtils.clone(datasets)),
                new QuickSort(ArrayUtils.clone(datasets)),
                new InsertSort(ArrayUtils.clone(datasets)),
                new ShellSort(ArrayUtils.clone(datasets)),
                new SelectSort(ArrayUtils.clone(datasets)),
                new BubbleSort(ArrayUtils.clone(datasets)),
                new HeapSort(ArrayUtils.clone(datasets)),
        };
        for (BaseSort baseSort : baseSorts) {
            System.out.println(baseSort.getClass().getName());
            baseSort.sortTimeIt(baseSort.getClass().getName());
            System.out.println(baseSort);
        }
    }
}
