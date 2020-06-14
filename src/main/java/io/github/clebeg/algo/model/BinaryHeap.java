package io.github.clebeg.algo.model;

import java.util.Comparator;

/**
 * 二叉堆 通过数组的方式存储
 * 索引规律：i 是根节点 2*i+1 是左孩子 2*i+2 是右孩子
 * i 不能超过 heapSize - 1
 * @param <E>
 */
public class BinaryHeap<E extends Comparable> implements Heap<E> {
    private int heapSize;
    private E[] heapArray;
    private int realCapacity; // 真实容量
    private static final int DEFAULT_CAPACITY = 16;
    private static final Double DEFAULT_EXP_RATE = 0.8;
    private Comparator<E> comparator;

    public BinaryHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.heapArray = (E[]) new Object[DEFAULT_CAPACITY];
        this.realCapacity = DEFAULT_CAPACITY;
        this.heapSize = 0;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BinaryHeap Size=");
        sb.append(this.heapSize);
        sb.append(",");
        for (int i = 0; i < this.heapSize; i++) {
            sb.append(i);
            sb.append(":");
            sb.append(this.heapArray[i]);
            sb.append("\t");
        }
        return sb.toString();
    }

    public int size() {
        return this.heapSize;
    }

    public void clear() {
        for (int i = 0; i < this.heapArray.length; i++) {
            this.heapArray[i] = null;
        }
        this.heapSize = 0;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public E getTop() {
        if (this.isEmpty()) {
            return null;
        }
        return this.heapArray[0];
    }

    public E removeTop() {
        if (this.isEmpty()) {
            return null;
        }
        E top = this.heapArray[0];
        this.heapArray[0] = this.heapArray[this.heapSize - 1];
        this.heapArray[this.heapSize-1] = null;
        this.heapSize --;
        this.shiftDown(0);
        return top;
    }

    public void put(E ele) {
        this.heapArray[this.heapSize++] = ele;
        this.shiftUp(this.heapSize - 1);
        if(this.realCapacity*DEFAULT_CAPACITY < this.heapSize) {
            this.expansion();
        }
    }

    private void expansion() {
        E[] capacityArray = (E[]) new Object[this.realCapacity + DEFAULT_CAPACITY];
        for (int i = 0; i < this.heapArray.length; i++) {
            capacityArray[i] = this.heapArray[i];
        }
        this.heapArray = capacityArray;
    }

    private void shiftUp(int index) {
        int curIndex = index;
        E element = this.heapArray[curIndex];
        while (curIndex > 0) {
            int fatherInd = (curIndex - 1)/2;
            if (comparator.compare(this.heapArray[fatherInd], element) < 0) {
                this.heapArray[curIndex] = this.heapArray[fatherInd];
                curIndex = fatherInd;
            } else break;
        }
        this.heapArray[curIndex] = element;
    }
    private void shiftDown(int index) {
        // 获取当前元素
        int curIndex = index;
        E element = this.heapArray[curIndex];
        while ((curIndex << 1) + 1 < this.heapSize) {
            // 获取左右子孩子
            int leftChildInd = (curIndex << 1) + 1;
            int rightChildInd = leftChildInd + 1;
            int tmpMaxInd = leftChildInd;
            if (rightChildInd < this.heapSize) {
                if (this.comparator.compare(this.heapArray[leftChildInd],
                        this.heapArray[rightChildInd]) < 0) {
                    tmpMaxInd = rightChildInd;
                }
            }
            if (this.comparator.compare(this.heapArray[tmpMaxInd], element) > 0) {
                this.heapArray[curIndex] = this.heapArray[tmpMaxInd];
                curIndex = tmpMaxInd;
            } else break;
        }
        this.heapArray[curIndex] = element;
    }

    /**
     * 替换掉堆顶元素，并且重新让堆恢复
     * @return
     */
    public E replaceTop(E ele) {
        if (this.isEmpty()) {
            return null;
        }
        E top = this.heapArray[0];
        this.heapArray[0] = ele;
        this.shiftDown(0);
        return top;
    }
}
