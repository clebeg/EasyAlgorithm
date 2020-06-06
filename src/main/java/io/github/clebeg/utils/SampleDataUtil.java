package io.github.clebeg.utils;

import org.apache.commons.lang3.RandomUtils;

public class SampleDataUtil {
    /**
     * 生成随机整数序列 范围是 [begin, end)
     * @param randNum 数据总量
     * @param begin 数据最小值
     * @param end 数据最大值
     * @return 随机生成的整数序列
     */
    public static Integer[] randIntArray(Integer randNum, Integer begin, Integer end) {
        Integer[] rands = new Integer[randNum];
        for (int i = 0; i < randNum; i++) {
            rands[i] = RandomUtils.nextInt(begin, end);
        }
        return rands;
    }

    public static void printArray(Object[] arrays) {
        for (Object array : arrays) {
            System.out.printf("%s ", array);
        }
    }

    public static void main(String[] args) {
        printArray(randIntArray(100, 1, 5));
    }
}
