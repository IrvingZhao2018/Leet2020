package DP.stoneGame.IV;

import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Integer, Boolean> memo = new HashMap<>();
    boolean[] dp;
    public boolean winnerSquareGame(int n) {
        if(memo.get(n) != null) return memo.get(n);
        int sqrtn = (int)Math.sqrt(n);
        if(sqrtn * sqrtn == n){
            memo.put(n, true);
            return true;
        }
        for(int i = 1; i * i < n; i++){
            if(!winnerSquareGame(n - i * i)){
                memo.put(n, true);
                return true;
            }
        }
        memo.put(n, false);
        return false;
    }

    public boolean winnerSquareGame2(int n) {
        dp = new boolean[n + 1];
        for(int i = 1; i <= n; i++){
            for(int k = 1; i - k * k >= 0; k++){
                if(!dp[i - k * k]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.winnerSquareGame2(4));
        for(boolean flag : solution.dp) System.out.println(flag);
        System.out.println(solution.memo);
    }
}
