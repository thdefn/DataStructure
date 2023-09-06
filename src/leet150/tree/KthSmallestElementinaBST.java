package leet150.tree;

import java.util.ArrayList;

public class KthSmallestElementinaBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static ArrayList<Integer> nums = new ArrayList<>();
    public static int target;

    public static int kthSmallest(TreeNode root, int k) {
        nums = new ArrayList<>();
        target = k;
        recursive(root);
        return nums.get(k - 1);
    }

    public static void recursive(TreeNode node) {
        if(nums.size() == target) return;
        if (node.left != null) recursive(node.left);
        nums.add(node.val);
        if (node.right != null) recursive(node.right);
    }


    public static void main(String[] args) {
        TreeNode left = new TreeNode(2, new TreeNode(1), null);
        TreeNode root = new TreeNode(3, left, new TreeNode(4));
        System.out.println(kthSmallest(root, 1));
    }

}
