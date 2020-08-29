package tree.leet404SumOfLeft;

import tree.TreeNode;

class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                TreeNode predecessor = cur.left;
                if(predecessor.left == null && predecessor.right == null)
                    sum += predecessor.val;
                while(predecessor.right != null && predecessor.right != cur)
                    predecessor = predecessor.right;
                if(predecessor.right == null){
                    predecessor.right = cur;
                    cur = cur.left;
                } else {
                    predecessor.right = null;
                    cur = cur.right;
                }
            }
        }
        return sum;
    }
}