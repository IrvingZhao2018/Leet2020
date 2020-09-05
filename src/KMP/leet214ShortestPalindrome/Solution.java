package KMP.leet214ShortestPalindrome;

public class Solution {
    public String shortestPalindromeKMP(String s) {
        StringBuilder rev = new StringBuilder(s).reverse();
        StringBuilder builder = new StringBuilder(s).append('#').append(rev.toString());
        int n = builder.length();
        int[] dp = new int[n];
        for(int i = 1; i < n; i++){
            int j = dp[i - 1];
            while(j > 0 && builder.charAt(i) != builder.charAt(j)) j = dp[j - 1];
            if(builder.charAt(i) == builder.charAt(j)) j++;
            dp[i] = j;
        }
        return rev.substring(0, rev.length() - dp[n - 1]) + s;
    }

    public String shortestPalindromeRecursive(String s) {
        int n = s.length();
        int i = 0;
        for (int j = n - 1; j >= 0; j--) {
            if (s.charAt(i) == s.charAt(j))
                i++;
        }
        if (i == n)
            return s;
        StringBuilder remain_rev = new StringBuilder(s.substring(i, n));
        remain_rev.reverse();
        return remain_rev.toString() + shortestPalindromeRecursive(s.substring(0, i)) + s.substring(i);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.shortestPalindromeKMP("aacecaaa"));
        System.out.println(solution.shortestPalindromeRecursive("aacecaaa"));
        System.out.println(solution.shortestPalindromeKMP("babbbabbaba"));
        System.out.println(solution.shortestPalindromeRecursive("babbbabbaba"));
    }
}
