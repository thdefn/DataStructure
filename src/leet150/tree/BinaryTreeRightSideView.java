package leet150.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeRightSideView {
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

        public static Map<Integer, Integer> map;

        public static List<Integer> rightSideView(TreeNode root) {
            map = new HashMap<>();
            if (root != null) recursive(root, 0);
            return new ArrayList<>(map.values());
        }

        public static void recursive(TreeNode node, int depth) {
            map.put(depth, node.val);
            if (node.left != null)
                recursive(node.left, depth + 1);
            if (node.right != null)
                recursive(node.right, depth + 1);
        }

        public static void main(String[] args) {
            TreeNode right = new TreeNode(3, null, null);
            TreeNode left = new TreeNode(2, new TreeNode(4), null);
            TreeNode root = new TreeNode(1, left, right);
            for (int i:
            rightSideView(root)) {
                System.out.println(i);
            }
        }
    }
}
