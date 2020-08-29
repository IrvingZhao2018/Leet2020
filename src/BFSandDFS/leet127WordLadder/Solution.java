package BFSandDFS.leet127WordLadder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(solution.ladderLength(beginWord, endWord, wordList));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordDict = new HashSet<>(wordList);
        //No Path
        if (!wordDict.contains(endWord)) return 0;
        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);
        int count = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            count++;
            //Expand smaller set
            if (beginSet.size() > endSet.size()) {
                Set<String> tmpSet = beginSet;
                beginSet = endSet;
                endSet = tmpSet;
            }
            //No way back
            wordDict.removeAll(beginSet);
            Set<String> next = new HashSet<>();
            for (String s : beginSet) {
                char[] cArr = s.toCharArray();
                for (int i = 0; i < cArr.length; i++) {
                    char c = cArr[i];
                    //BackTrack
                    for (char cc = 'a'; cc <= 'z'; cc++) {
                        if (cc == c) continue;
                        cArr[i] = cc;
                        String node = new String(cArr);
                        if (wordDict.contains(node)) {
                            if (endSet.contains(node)) return count;
                            next.add(node);
                        }
                    }
                    cArr[i] = c;
                }
            }
            beginSet = next;
        }
        return 0;
    }
}
