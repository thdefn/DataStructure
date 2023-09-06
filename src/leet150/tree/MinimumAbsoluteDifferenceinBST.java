package leet150.tree;

import java.util.ArrayList;

public class MinimumAbsoluteDifferenceinBST {

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

    static ArrayList<Integer> values = new ArrayList<>();

    public static int getMinimumDifference(TreeNode root) {
        return recursive(root);
    }

    public static int recursive(TreeNode node) {
        if (node == null) return Integer.MAX_VALUE;
        values.add(node.val);
        int prevMin = Math.min(recursive(node.left), recursive(node.right));
        values.remove(values.size() - 1);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < values.size(); i++)
            min = Math.min(min, Math.abs(values.get(i) - node.val));
        return Math.min(prevMin, min);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(48, new TreeNode(12), new TreeNode(49));
        TreeNode root = new TreeNode(1, new TreeNode(0), n1);
        System.out.println(getMinimumDifference(root));
    }
}
