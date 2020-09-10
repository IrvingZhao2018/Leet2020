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

    public int minCutDP2D(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] cut = new int[n];
        for(int i = 0; i < n; i++){
            int min = n;
            for(int j = 0; j <= i; j++){
                if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])){
                    dp[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, 1 + cut[j - 1]);
                }
            }
            cut[i] = min;
        }
        return cut[n - 1];
    }

    public int minCutDP1D(String s) {
        int n = s.length();
        int[] cut = new int[n + 1];
        for(int i = 0; i <= n; i++)
            cut[i] = i - 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; i - j >= 0 && i + j < n && s.charAt(i - j) == s.charAt(i + j); j++){
                cut[i + j + 1] = Math.min(cut[i + j + 1], 1 + cut[i - j]);
            }
            for(int j = 1; i - j + 1 >= 0 && i + j < n && s.charAt(i - j + 1) == s.charAt(i + j); j++){
                cut[i + j + 1] = Math.min(cut[i + j + 1], 1 + cut[i - j + 1]);
            }
        }
        return cut[n];
    }



}