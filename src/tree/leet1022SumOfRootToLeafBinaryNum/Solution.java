package tree.leet1022SumOfRootToLeafBinaryNum;

import tree.TreeNode;

class Solution {
    public int sumRootToLeaf(TreeNode root) {
        int sum = 0;
        int cur = 0;
        while(root != null){
            if(root.left == null){
                cur = cur * 2 + root.val;
                root = root.right;
                if(root == null) sum += cur;
            } else {
                TreeNode predecessor = root.left;
                int step = 1;
                while(predecessor.right != null && predecessor.right != root){
                    predecessor = predecessor.right;
                    step++;
                }
                if(predecessor.right == root){
                    if(predecessor.left == null) sum += cur;
                    cur = cur >> step;
                    predecessor.right = null;
                    root = root.right;
                } else {
                    predecessor.right = root;
                    cur = cur * 2 + root.val;
                    root = root.left;
                }
            }
        }
        return sum;
    }
}
