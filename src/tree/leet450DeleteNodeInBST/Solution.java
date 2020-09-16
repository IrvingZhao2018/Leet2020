package tree.leet450DeleteNodeInBST;

import tree.TreeNode;

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)
            return null;
        if(root.val < key){
            root.right = deleteNode(root.right, key);
        } else if(key < root.val){
            root.left = deleteNode(root.left, key);
        } else { // root.val == key
            if(root.left == null && root.right == null)
                return null;
            else if (root.left != null){
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            } else { //if(root.right != null)
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
        }
        return root;
    }

    private int successor(TreeNode root){
        root = root.right;
        while(root.left != null)
            root = root.left;
        return root.val;
    }

    private int predecessor(TreeNode root){
        root = root.left;
        while(root.right != null){
            root = root.right;
        }
        return root.val;
    }
}
