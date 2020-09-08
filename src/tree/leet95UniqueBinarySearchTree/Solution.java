package tree.leet95UniqueBinarySearchTree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        for(TreeNode root : solution.generateTrees(3)){
            root.prettyPrintTree(root);
        }
    }

    public List<TreeNode> generateTrees(int n) {
//		n	list for n nodes
        if (n == 0) return new ArrayList<TreeNode>();
        Map<Integer, List<TreeNode>> resultMap = new HashMap<>();
        resultMap.put(0, new ArrayList<TreeNode>() {{
            add(null);
        }});
        dfs(n, resultMap);
        return resultMap.get(n);
    }

    private List<TreeNode> dfs(int n, Map<Integer, List<TreeNode>> resultMap) {
        if (resultMap.containsKey(n)) return resultMap.get(n);
        List<TreeNode> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<TreeNode> left = dfs(i - 1, resultMap);
            List<TreeNode> right = dfs(n - i, resultMap);
            for (TreeNode lroot : left) {
                for (TreeNode rroot : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = lroot;
                    root.right = cloneNode(rroot, i);
                    list.add(root);
                }
            }
        }
        resultMap.put(n, list);
        return list;
    }

    private TreeNode cloneNode(TreeNode root, int offset) {
        if (root == null) return null;
        TreeNode newRoot = new TreeNode(root.val + offset);
        newRoot.left = cloneNode(root.left, offset);
        newRoot.right = cloneNode(root.right, offset);
        return newRoot;
    }
}
