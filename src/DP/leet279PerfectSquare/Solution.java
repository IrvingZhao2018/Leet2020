package DP.leet279PerfectSquare;

import java.util.*;

class Solution {

    Map<Integer, Integer> memo = new HashMap<>();

    public int numSquaresMemoTopDown(int n) {
        if (n < 3) return n;
        if (memo.get(n) != null) return memo.get(n);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {
            min = Math.min(min, numSquaresMemoTopDown(n - i * i) + 1);
        }
        memo.put(n, min);
        return min;
    }

    public int numSquaresBFS(int n) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        int level = 1;
        while (!set.isEmpty()) {
            Set<Integer> nextSet = new HashSet<>();
            for (int num : set) {
                for (int i = 1; i * i <= num; i++) {
                    int nextNum = num - i * i;
                    if (nextNum == 0) return level;
                    nextSet.add(nextNum);
                }
            }
            level++;
            set = nextSet;
        }
        return level;
    }


    public int numSquaresDPBottomUp(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // bottom case
        dp[0] = 0;

        // pre-calculate the square numbers.
        int max_square_index = (int) Math.sqrt(n) + 1;
        int[] square_nums = new int[max_square_index];
        for (int i = 1; i < max_square_index; ++i) {
            square_nums[i] = i * i;
        }

        for (int i = 1; i <= n; ++i) {
            for (int s = 1; s < max_square_index; ++s) {
                if (i < square_nums[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
            }
        }
        return dp[n];
    }

}