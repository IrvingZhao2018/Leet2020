package BFSandDFS.leet126WordLadderII;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println(solution.findLadders(beginWord, endWord, wordList2));
    }

    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        List<List<String>> res = new ArrayList<List<String>>();
        Set<String> dict = new HashSet<>(wordList);
        if(!dict.contains(end)) return res;
        // hash set for both ends
        Set<String> beginSet = new HashSet<String>();
        Set<String> endSet = new HashSet<String>();

        // initial words in both ends
        beginSet.add(start);
        endSet.add(end);

        // we use a map to help construct the final result
        Map<String, List<String>> pathMap = new HashMap<String, List<String>>();

        // build the map
        helper(dict, beginSet, endSet, pathMap, false);


        List<String> path = new ArrayList<String>(Arrays.asList(start));
        System.out.println(pathMap);
        // recursively build the final result
        generateList(start, end, pathMap, path, res);

        return res;
    }

    boolean helper(Set<String> dict, Set<String> beginSet, Set<String> endSet, Map<String, List<String>> pathMap, boolean flip) {
        if (beginSet.isEmpty()) {
            return false;
        }

        if (beginSet.size() > endSet.size()) {
            return helper(dict, endSet, beginSet, pathMap, !flip);
        }

        // remove words on current both ends from the dict
        dict.removeAll(beginSet);
        dict.removeAll(endSet);

        // as we only need the shortest paths
        // we use a boolean value help early termination
        boolean done = false;

        // set for the next level
        Set<String> nextSet = new HashSet<String>();

        // for each string in end 1
        for (String str : beginSet) {
            for (int i = 0; i < str.length(); i++) {
                char[] chars = str.toCharArray();

                // change one character for every position
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;

                    String word = new String(chars);

                    // make sure we construct the tree in the correct direction
                    String key = flip ? word : str;
                    String val = flip ? str : word;

                    List<String> list = pathMap.containsKey(key) ? pathMap.get(key) : new ArrayList<String>();

                    if (endSet.contains(word)) {
                        done = true;

                        list.add(val);
                        pathMap.put(key, list);
                    }

                    if (!done && dict.contains(word)) {
                        nextSet.add(word);

                        list.add(val);
                        pathMap.put(key, list);
                    }
                }
            }
        }

        // early terminate if done is true
        return done || helper(dict, endSet, nextSet, pathMap, !flip);
    }

    void generateList(String start, String end, Map<String, List<String>> pathMap, List<String> path, List<List<String>> res) {
        if (start.equals(end)) {
            res.add(new ArrayList<String>(path));
            return;
        }

        // need this check in case the diff between start and end happens to be one
        // e.g "a", "c", {"a", "b", "c"}
        if (!pathMap.containsKey(start)) {
            return;
        }

        for (String word : pathMap.get(start)) {
            path.add(word);
            generateList(word, end, pathMap, path, res);
            path.remove(path.size() - 1);
        }
    }
}
