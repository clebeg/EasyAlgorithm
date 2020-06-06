package io.github.clebeg.algo.sort;

import io.github.clebeg.algo.sort.BaseSort;
import io.github.clebeg.algo.sort.InsertSort;
import io.github.clebeg.algo.sort.QuickSort;
import io.github.clebeg.algo.sort.ShellSort;
import io.github.clebeg.utils.SampleDataUtil;

public class SortAlgoTest {
    public static void main(String[] args) {
        Integer[] datasets = SampleDataUtil.randIntArray(200000, 10, 2000000);
        BaseSort[] baseSorts = {
                new InsertSort(datasets),
                new ShellSort(datasets),
                new QuickSort(datasets),
        };
        for (BaseSort baseSort : baseSorts) {
            System.out.println(baseSort.getClass().getName());
            baseSort.sortTimeIt(baseSort.getClass().getName());
            System.out.println(baseSort);
        }
    }
}
