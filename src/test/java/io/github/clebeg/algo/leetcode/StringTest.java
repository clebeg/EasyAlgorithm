package io.github.clebeg.algo.leetcode;

public class StringTest {
    private static final char beginU = 'A';
    private static final char beginS = 'a';
    private static final char begin0 = '0';

    public static void main(String[] args) {
        String s = "Marge, let's \"[went].\" I await {news} telegram.";
        System.out.println(String.format("%c", beginS + 25));
        System.out.println(isPalindrome(s));
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
}
