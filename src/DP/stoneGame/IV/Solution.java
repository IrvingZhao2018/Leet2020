package DP.stoneGame.IV;

import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Integer, Boolean> memo = new HashMap<>();
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

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.winnerSquareGame(17));
        System.out.println(solution.memo);
    }
}
