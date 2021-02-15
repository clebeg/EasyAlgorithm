package io.github.clebeg.algo.leetcode;

public class LeetCode125Test {
    private static final char beginU = 'A';
    private static final char beginS = 'a';
    private static final char begin0 = '0';

    public static void main(String[] args) {
        String s = "Marge, let's \"[went].\" I await {news} telegram.";
        System.out.println(String.format("%c", beginS + 25));
//        System.out.println(isPalindrome(s));
        LeetCode125Test cur = new LeetCode125Test();
        cur.minMutation("AACCGGTT", "AAACGGTA", new String[] {"AACCGATT","AACCGATA","AAACGATA","AAACGGTA"});
    }

    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < s.length() && isSkip(s.charAt(i))) {
                i++;
            }
            while (j >= 0 && isSkip(s.charAt(j))) {
                j--;
            }
            if (i >= j) {
                break;
            }
            if (isEqual(s.charAt(i), s.charAt(j))) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }
    private static boolean isSkip(char c) {
        if ((c >= beginS && c <= beginS + 25)
                || (c >= beginU && c <= beginU + 25)
                || (c >= begin0 && c <= begin0 + 9)) {
            return false;
        }
        return true;
    }

    private static boolean isEqual(char c, char d) {
        if (c == d) {
            return true;
        }
        if (c >= begin0 && c <= begin0 + 9) {
            return false;
        } else if (Math.abs(c - d) == (beginS - beginU)) {
            return true;
        } else {
            return false;
        }
    }

    public int minMutation(String start, String end, String[] bank) {
        if (start.length() != 8 || end.length() != 8) return -1;
        int res = process(0, start, end, bank);
        if (res == Integer.MAX_VALUE) return -1;
        else return res;
    }

    private int process(int changeTimes, String cur, String end, String[] bank) {
        if (cur.equals(end)) return changeTimes;
        int minTimes = Integer.MAX_VALUE;
        char[] chars = cur.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != end.charAt(i)) {
                char tmp = chars[i];
                chars[i] = end.charAt(i);
                String mid = new String(chars);
                if (isInBank(mid, bank)) {
                    minTimes = Math.min(
                            process(changeTimes + 1, mid, end, bank),
                            minTimes);
                }
                chars[i] = tmp;
            }
        }
        return minTimes;
    }

    private boolean isInBank(String cur, String[] bank) {
        for (String s : bank) {
            if (s.equals(cur)) return true;
        }
        return false;
    }
}
