package slidingWindow.leet76MinWindow;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {

        if (s.length() == 0 || t.length() == 0) return "";

        Map<Character, Integer> counter = new HashMap<>();
        for (char c : t.toCharArray())
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        int len = t.length();

        int start = 0;
        int min = s.length() + 1;
        int resStart = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (counter.containsKey(c)) {
                int cnt = counter.get(c);
                if (cnt > 0) len--;
                counter.put(c, cnt - 1);
            }
            while (len == 0) { // valid
                if (end - start + 1 < min) {
                    resStart = start;
                    min = end - start + 1;
                }
                char delete = s.charAt(start);
                if (counter.containsKey(delete)) {
                    if (counter.get(delete) == 0) len++; // invalid
                    counter.put(delete, counter.get(delete) + 1);
                }
                start++;
            }
        }
        return min > s.length() ? "" : s.substring(resStart, resStart + min);
    }
}
