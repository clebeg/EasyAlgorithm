package io.github.clebeg.algo.strutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StrMatch {
    private final static int HASH_SIZE = 10000019;

    public static void main(String[] args) {
        String target = "abcdab";
        String match = "ab";
        System.out.println(hashMatch(target, match));
    }

    /**
     * 使用暴力方式匹配，时间复杂度为 O(NM)
     * @param target 匹配目标串
     * @param match 匹配子串
     * @return 匹配到的位置列表
     */
    public static List<Integer> forceMatch(String target, String match) {
        List<Integer> res = new ArrayList<>();
        if (target.length() == 0 || match.length() == 0) return res;

        int n = target.length(), m = match.length();
        // 0123 //01
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m) {
                if (target.charAt(i+j) != match.charAt(j)) break;
                j++;
            }
            if (j == m) res.add(i);
        }
        return res;
    }

    /**
     * kmp 算法
     * @param target
     * @param match
     * @return
     */
    public static List<Integer> kmpMatch(String target, String match) {
        List<Integer> res = new ArrayList<>();
        int[] next = buildNext(match);
        int n = target.length(), m = match.length();
        int i = 0, j = 0;
        while (i < n && m > 0) {
            while (j == m || (j >= 0 && target.charAt(i) != match.charAt(j))) {
                if (j == m) j = next[m-1];
                else j = next[j];
            }
            i++;
            j++;
            if (j == m) res.add(i - m);
        }
        return res;
    }

    private static int[] buildNext(String match) {
        int[] next = new int[match.length()];
        int i = 0, j = -1;
        while (i < match.length()) {
            next[i] = j;
            while (j >= 0 && match.charAt(i) != match.charAt(j)) j = next[j];
            i++;
            j++;
        }
        return next;
    }

    /**
     * 利用sunday算法计算子串匹配
     * @param target 目标串
     * @param match 子串
     * @return 返回匹配到的子串起始索引位置
     * abcabdababdd
     * abab
     *
     * a: 3 b: 4
     * abbaadababdd
     * abab
     *
     * abbaadababdd
     *   abab
     */
    public static List<Integer> sundayMatch(String target, String match) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> posMap = buildPosMap(match);
        int i = 0, j = 0, m = match.length();
        int tLen = target.length(), mLen = match.length();
        while (i < tLen && mLen > 0) {
            if (target.charAt(i) != match.charAt(j)) {
                // 如果不相等 对齐处理
                i = m - posMap.getOrDefault(target.charAt(i), -1);
                j = -1;
                m = i + mLen + 1;
            }
            i++;
            j++;

            if (j == mLen) {
                res.add(i - j);
                j = 0;
            }
        }
        return res;
    }

    /**
     * 得到匹配
     * @param match
     * @return
     */
    private static Map<Character, Integer> buildPosMap(String match) {
        Map<Character, Integer> res = new HashMap<>();
        for (int i = 0; i < match.length(); i++)
            res.put(match.charAt(i), i+1);
        return res;
    }

    /**
     * RabinKarp
     * @param target
     * @param match
     * @return
     */
    public static List<Integer> hashMatch(String target, String match) {
        List<Integer> res = new ArrayList<>();
        int tLen = target.length(), mLen = match.length();
        if (mLen == 0 || tLen == 0 || mLen > tLen) return res;
        int matchHash = buildHash(match, 0, mLen);
        int curHash = buildHash(target, 0, mLen);
        int base = 1;
        for (int i = 1; i < mLen; i++) {
            base = (base * 10) % HASH_SIZE;
        }

        for (int i = 0; i <= tLen - mLen; i++) {
            if (matchHash == curHash) {
                int j = 0;
                for (; j < mLen; j++) {
                    if (target.charAt(i+j) != match.charAt(j)) break;
                }
                if (j == mLen) res.add(i);
            }
            curHash = buildNextHash(curHash, target, i, mLen, base);
        }
        return res;
    }

    private static int buildNextHash(int curHash, String target, int i, int mLen, int base) {
        if (i + mLen >= target.length()) return 0;
        int tmp =  curHash + ((target.charAt(i) - 'a') * (HASH_SIZE - base)) % HASH_SIZE;
        return (tmp * 10 + target.charAt(i + mLen) - 'a') % HASH_SIZE;
    }

    private static int buildHash(String match, int begin, int end) {
        int hashCode = match.charAt(0) - 'a';
        for (int i = begin + 1; i < end; i++)
            hashCode = (hashCode * 10 + (match.charAt(i) - 'a')) % HASH_SIZE;
        return hashCode;
    }

}
