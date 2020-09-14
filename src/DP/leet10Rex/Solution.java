package DP.leet10Rex;

class Solution {
    public boolean isMatchMemo(String string, String pattern) {
        int m = string.length();
        int n = pattern.length();

        Boolean[][] memo = new Boolean[m + 1][n + 1];

        return dfs(0, 0, string, pattern, memo);
    }

    private boolean dfs(int idxS, int idxP, String string, String pattern, Boolean[][] memo){
        if(memo[idxS][idxP] != null) return memo[idxS][idxP];

        boolean res;
        if(idxP == pattern.length()) {
            res = idxS == string.length();
        } else {
            boolean firstMatch = idxS < string.length() && (string.charAt(idxS) == pattern.charAt(idxP) || pattern.charAt(idxP) == '.');
            if(idxP + 1 < pattern.length() && pattern.charAt(idxP + 1) == '*'){
                res = dfs(idxS, idxP + 2, string, pattern, memo) || (firstMatch && dfs(idxS + 1, idxP, string, pattern, memo));
            } else {
                res = firstMatch && (dfs(idxS + 1, idxP + 1, string, pattern, memo));
            }
        }
        memo[idxS][idxP] = res;
        return res;
    }

    public boolean isMatchDP(String string, String pattern) {
        boolean[][] dp = new boolean[string.length() + 1][pattern.length() + 1];
        dp[string.length()][pattern.length()] = true;
        for(int idxS = string.length(); idxS >= 0; idxS--){
            for(int idxP = pattern.length() - 1; idxP >= 0; idxP--){
                boolean cur = idxS < string.length() && (string.charAt(idxS) == pattern.charAt(idxP) || pattern.charAt(idxP) == '.');
                if(idxP + 1 < pattern.length() && pattern.charAt(idxP + 1) == '*'){
                    dp[idxS][idxP] = dp[idxS][idxP + 2] || (cur && dp[idxS + 1][idxP]);
                } else {
                    dp[idxS][idxP] = cur && dp[idxS + 1][idxP + 1];
                }
            }
        }
        return dp[0][0];
    }



}
