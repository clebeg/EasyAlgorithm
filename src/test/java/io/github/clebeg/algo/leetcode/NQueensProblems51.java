package io.github.clebeg.algo.leetcode;

import java.util.*;

public class NQueensProblems51 {
    // 之前出现过的行
    private static Set<Integer> colSet = new HashSet<>();
    // 之前出现过的na
    private static Set<Integer> naSet = new HashSet<>();
    // 之前出现过的撇
    private static Set<Integer> pieSet = new HashSet<>();

    public static void main(String[] args) {
        NQueensProblems51 cur = new NQueensProblems51();
        List<List<String>> res = cur.solveNQueens(4);
        for (List<String> re : res) {
            System.out.println(re);
        }
    }

    public List<List<String>> solveNQueens(int n) {
        char[][] begin = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                begin[i][j] = '.';
            }
        }
        // 返回结果
        List<List<String>> res = new ArrayList<>();
        process(res, begin, 0, n);
        return res;
    }

    private void process(List<List<String>> res,
                         char[][] cur,
                         int row,
                         int n) {
        if (row >= n) {
            res.add(change(cur));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (colSet.contains(col)
                || naSet.contains(col + row)
                || pieSet.contains(col - row)) {
                continue;
            }
            colSet.add(col);
            naSet.add(col + row);
            pieSet.add(col - row);
            cur[col][row] = 'Q';

            process(res, cur, row + 1, n);

            colSet.remove(col);
            naSet.remove(col + row);
            pieSet.remove(col - row);
            cur[col][row] = '.';
        }
    }

    private List<String> change(char[][] cur) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < cur.length; i++) {
            StringBuffer tmp = new StringBuffer();
            for (int j = 0; j < cur.length; j++) {
                tmp.append(cur[i][j]);
            }
            res.add(tmp.toString());
        }
        return res;
    }
}
