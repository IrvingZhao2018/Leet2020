package KMP.leet28strStr;

public class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.equals("")) return 0;
        if(haystack.equals("")) return -1;

        int[] prefix = KMPPrefix(needle);
        int n = haystack.length();
        for(int i = 0, j = 0; i < n; i++){
            while(j > 0 && haystack.charAt(i) != needle.charAt(j)) j = prefix[j - 1];
            if(haystack.charAt(i) == needle.charAt(j)) j++;
            if(j == prefix.length) return i - j + 1;
        }
        return -1;
    }

    private int[] KMPPrefix(String s){
        int n = s.length();
        int[] dp = new int[n];
        for(int i = 1; i < n; i++){
            int j = dp[i - 1];
            while(j > 0 && s.charAt(i) != s.charAt(j)) j = dp[j - 1];
            if(s.charAt(i) == s.charAt(j)) j++;
            dp[i] = j;
        }
        return dp;
    }
}
