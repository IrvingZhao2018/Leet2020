package tree.leet144preorder;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode builder = new TreeNode();
        String test1 = "[1,null,2,3,4,5]";
        TreeNode root = builder.stringToTreeNode(test1);
        builder.prettyPrintTree(root);

        System.out.println(solution.preorderTraversalMorris(root));
        System.out.println(solution.preorderTraversalIterative(root));
    }


    public List<Integer> preorderTraversalIterative(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.add(node.val);
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return output;
    }

    /*
     * Time complexity :
     * we visit each predecessor exactly twice descending down from the node,
     * thus the time complexity is O(N), where NN is the number of nodes, i.e. the size of tree.
     *
     * Space complexity :
     * we use no additional memory for the computation itself,
     * but output list contains NN elements, and thus space complexity is O(N).*/
    public List<Integer> preorderTraversalMorris(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();

        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                output.add(node.val);
                node = node.right;
            } else {
                TreeNode predecessor = node.left;
                while ((predecessor.right != null) && (predecessor.right != node)) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    output.add(node.val);
                    predecessor.right = node;
                    node = node.left;
                } else {
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }
        return output;
    }
}
