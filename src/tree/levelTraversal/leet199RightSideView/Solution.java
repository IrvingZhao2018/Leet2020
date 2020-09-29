package tree.levelTraversal.leet199RightSideView;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root == null) return res;
        dfs(root, 0, res);
        return res;
    }
    private void dfs(TreeNode root, int lv, List<Integer> res){
        if(lv == res.size()) res.add(root.val);
        if(root.right != null) dfs(root.right, lv + 1, res);
        if(root.left != null) dfs(root.left, lv + 1, res);
    }

    public List<Integer> rightSideView2Queue(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        if(root == null) return res;
        queue.addLast(root);
        while(!queue.isEmpty()){
            LinkedList<TreeNode> nextLevel = new LinkedList<>();
            int last = 0;
            while(!queue.isEmpty()){
                TreeNode node = queue.pollFirst();
                last = node.val;
                if(node.left != null) nextLevel.addLast(node.left);
                if(node.right != null) nextLevel.addLast(node.right);
            }
            queue = nextLevel;
            res.add(last);
        }
        return res;
    }
}