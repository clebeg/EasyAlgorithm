package io.github.clebeg.model;

import io.github.clebeg.model.BinaryHeap;

import java.util.Comparator;

public class HeapTest {
    public static void main(String[] args) {
        BinaryHeap<Integer> integerBinaryHeap = new BinaryHeap<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        integerBinaryHeap.put(56);
        integerBinaryHeap.put(40);
        integerBinaryHeap.put(30);
        integerBinaryHeap.put(60);
        integerBinaryHeap.put(98);
        integerBinaryHeap.put(20);
        integerBinaryHeap.put(45);
        System.out.println(integerBinaryHeap);
        integerBinaryHeap.removeTop();
        System.out.println(integerBinaryHeap);
        integerBinaryHeap.removeTop();
        System.out.println(integerBinaryHeap);
    }
}
