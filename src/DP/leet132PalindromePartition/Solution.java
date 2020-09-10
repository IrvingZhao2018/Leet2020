package DP.leet132PalindromePartition;

class Solution {
    public int minCutMemo(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= i; j++){
                if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1]))
                    dp[j][i] = true;
            }
        }
        Integer[] memo = new Integer[n + 1];
        memo[n] = 0;
        return backTrack(s, 0, dp, memo) - 1;
    }

    private int backTrack(String s, int start, boolean[][] dp, Integer[] memo){
        if(memo[start] != null) return memo[start];
        int min = s.length();
        for(int i = start; i < s.length(); i++){
            if(dp[start][i]){
                min = Math.min(min, 1 + backTrack(s, i + 1, dp, memo));
            }
        }
        memo[start] = min;
        return min;
    }



}