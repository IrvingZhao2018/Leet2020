package unionFind.leet128LongestConsecutiveSubsequence;

import java.util.*;

public class Solution {
    public int longestConsecutiveGreedy(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for(int num : nums) seen.add(num);
        int max = 0;
        for(int num : nums){
            if(!seen.contains(num - 1)){
                int cur = 1;
                while(seen.contains(num + 1)){
                    cur++;
                    num++;
                }
                max = Math.max(max, cur);
            }
        }
        return max;
    }
    public int longestConsecutive(int[] nums) {
        //  node  ,  parent
        Map<Integer, Integer> parentMap = new HashMap<>();
        Map<Integer, Integer> sizeMap = new HashMap<>();
        int max = 0;
        for(int num : nums){
            if(!parentMap.containsKey(num)){
                parentMap.put(num, num);
                sizeMap.put(num, 1);
                if(parentMap.containsKey(num - 1)) union(num - 1, num, parentMap, sizeMap);
                if(parentMap.containsKey(num + 1)) union(num, num + 1, parentMap, sizeMap);
                max = Math.max(max, sizeMap.get(find(num, parentMap)));
            }
        }
        System.out.println(parentMap);
        System.out.println(sizeMap);
        return max;
    }

    private void union(int x, int y, Map<Integer, Integer> parentMap, Map<Integer, Integer> sizeMap){
        int xParent = find(x, parentMap);
        int yParent = find(y, parentMap);
        if(xParent != yParent){
            int xSize = sizeMap.get(xParent);
            int ySize = sizeMap.get(yParent);
            if(xSize > ySize){
                parentMap.put(yParent, xParent);
                sizeMap.put(xParent, xSize + ySize);
            } else {
                parentMap.put(xParent, yParent);
                sizeMap.put(yParent, xSize + ySize);
            }
        }
    }

    private int find(int x, Map<Integer, Integer> parentMap){
        if(x != parentMap.get(x)) parentMap.put(x, find(parentMap.get(x), parentMap));
        return parentMap.get(x);
    }
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(-3, 2, 8, 5, 1, 7, -8, 2, -8, -4,
                -1, 6, -6, 9, 6, 0, -7, 4, 5, -4, 8, 2, 0, -2, -6, 9, -4, -1);
        Collections.sort(list);
        System.out.println(list);
        int[] test = {-3, 2, 8, 5, 1, 7, -8, 2, -8, -4,
                -1, 6, -6, 9, 6, 0, -7, 4, 5, -4, 8, 2, 0, -2, -6, 9, -4, -1};
        int[] test2 = {-8, -8, -7, -6, -6, -4, -4, -4,
                -3, -2, -1, -1, 0, 0, 1, 2, 2, 2, 4, 5, 5, 6, 6, 7, 8, 8, 9, 9};
        Solution solution = new Solution();
        System.out.println(solution.longestConsecutive(test));
        System.out.println(solution.longestConsecutiveGreedy(test));
    }
}
