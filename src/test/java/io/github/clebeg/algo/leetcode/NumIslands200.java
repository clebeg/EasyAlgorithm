package io.github.clebeg.algo.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 求有多少岛屿，基本思路
 * 1. 先将所有的1保存到一个set中
 * 2. 从set中随机取出一个数开始
 */
public class NumIslands200 {
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        NumIslands200 cur = new NumIslands200();
        System.out.println(cur.numIslands(grid));
    }


    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int landsNum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    landsNum++;
                    process(i, j, grid);
                }
            }
        }
        return landsNum;
    }

    private void process(int row,
                         int col,
                         char[][] grid) {
        if (row < 0 || row >= grid.length) return;
        if (col < 0 || col >= grid[0].length) return;
        if (grid[row][col] == '0') return;
        grid[row][col] = '1';
        process(row + 1, col, grid);
        process(row - 1, col, grid);
        process(row, col + 1, grid);
        process(row, col - 1, grid);
    }
}
