package tree.leet285InorderSuccessorInBST;

import tree.TreeNode;

class Solution {
    public TreeNode inorderSuccessorIterative(TreeNode root, TreeNode p) {
        if (p.right != null) {
            TreeNode node = p.right;
            while (node.left != null) node = node.left;
            return node;
        }
        TreeNode pre = null;
        TreeNode node = root;
        while (node != null) {
            if (node.val <= p.val) {
                node = node.right;
            } else {
                pre = node;
                node = node.left;
            }
        }
        return pre;
    }


    public TreeNode successorRecursive(TreeNode root, TreeNode p) {
        if (root == null)
            return null;

        if (root.val <= p.val) {
            return successorRecursive(root.right, p);
        } else {
            TreeNode left = successorRecursive(root.left, p);
            return (left != null) ? left : root;
        }
    }

}
