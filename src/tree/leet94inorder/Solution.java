package tree.leet94inorder;


import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode builder = new TreeNode();
        String test1 = "[1,null,2,3,4,5]";
        TreeNode root = builder.stringToTreeNode(test1);
        builder.prettyPrintTree(root);

        System.out.println(solution.inorderTraversalIterative(root));
    }

    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}

