package KMP.leet459RepeatedSubstringPattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public boolean repeatedSubstringPatternKMP(String s) {
        int n = s.length();
        int[] dp = new int[n];
        for(int i = 1; i < n; i++){
            int j = dp[i - 1];
            while(j > 0 && s.charAt(i) != s.charAt(j)) j = dp[j - 1];
            if(s.charAt(i) == s.charAt(j)) j++;
            dp[i] = j;
        }
        int period = n - dp[n - 1];
        return dp[n - 1] > 0 && n % period == 0;
    }

    public boolean repeatedSubstringPatternRegex(String s) {
        Pattern pat = Pattern.compile("^(.+)\\1+$");
         Matcher match = pat.matcher(s);
//         while(match.find()){
//             System.out.println(s.substring(match.start(), match.end()));
//         }
        return pat.matcher(s).matches();
    }

    public boolean repeatedSubstringPatternSubstring(String s) {
        return (s + s).substring(1, 2 * s.length() - 1).contains(s);
    }
}
