package io.github.clebeg.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence1143 {
    public static void main(String[] args) {
        LongestCommonSubsequence1143 cur = new LongestCommonSubsequence1143();
        cur.longestCommonSubsequence("abcde", "ace");
        Map<Integer, Integer> helper = new HashMap<>();
    }

    public int longestCommonSubsequence(String text1, String text2) {
        // 定义dp数组的含义为 text1 前i个字符 和 text2 前j个字符的最长公共子序列
        // 那么 dp[i+1][j+1] = max(dp[i][j] + if(i==j) 1 else 0, dp[i+1][j], dp[i][j+1])
        int row = text1.length();
        int col = text2.length();
        int[][] dpStates = new int[row+1][col+1];

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dpStates[i][j] = Math.max(
                        dpStates[i-1][j],
                        dpStates[i][j-1]
                );
                dpStates[i][j] = Math.max(
                        dpStates[i][j],
                        dpStates[i-1][j-1] + (text1.charAt(i-1) == text2.charAt(j-1) ? 1 : 0)
                );
            }
        }
        return dpStates[row][col];
    }
}
