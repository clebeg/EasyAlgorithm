package io.github.clebeg.algo.leetcode;

import java.util.*;

public class NSumProblemTest {
    public static void main(String[] args) {
        int[] test = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea84(test));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
            if (nums[i] > 0) break;
            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                int tmp = - (nums[j] + nums[k]);
                if (tmp == nums[i]) {
                    List<Integer> in = new ArrayList<>();
                    in.add(nums[i]);
                    in.add(nums[j++]);
                    in.add(nums[k--]);
                    res.add(in);
                } else if (tmp < nums[i]) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return new ArrayList<>(res);
    }

    public boolean isValid(String s) {
        HashMap<Character, Character> matchChar = new HashMap<>();
        matchChar.put(')', '(');
        matchChar.put(']', '[');
        matchChar.put('}', '{');
        Stack<Character> charStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if (matchChar.containsKey(tmp)) {
                if (charStack.pop() != matchChar.get(tmp)) return false;
            } else {
                charStack.push(tmp);
            }
        }
        return true;
    }

    public static int largestRectangleArea84(int[] heights) {
        Stack<Integer> minStack = new Stack<>();
        int[] rights = new int[heights.length];
        for (int i = heights.length - 1; i >= 0; i--) {
            if (minStack.isEmpty() || heights[i] > heights[minStack.peek()]) {
                minStack.push(i);
                rights[i] = 1;
            } else {
                while (!minStack.isEmpty() &&
                        heights[i] <= heights[minStack.peek()])
                    minStack.pop();
                rights[i] = minStack.isEmpty() ?
                        heights.length - i :
                        minStack.peek() - i;
                minStack.push(i);
            }
        }
        int maxArea = 0;
        int[] lefts = new int[heights.length];
        minStack.clear();
        for (int i = 0; i < heights.length; i++) {
            if (minStack.isEmpty() || heights[i] > heights[minStack.peek()]) {
                minStack.push(i);
                lefts[i] = 0;
            } else {
                while (!minStack.isEmpty() &&
                        heights[i] <= heights[minStack.peek()])
                    minStack.pop();
                lefts[i] = minStack.isEmpty() ? i : i - minStack.peek() - 1;
                minStack.push(i);
            }
            maxArea = Math.max(maxArea, heights[i] * (lefts[i] + rights[i]));
        }
        return maxArea;
    }
}
