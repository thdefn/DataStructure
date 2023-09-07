package leet150.tree;

import java.util.*;

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
    }

    public static Map<Integer, Integer> map;
    public static Queue<TreeNode> q;

    public static List<Integer> rightSideView(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) bfs(root, q, map);
        return new ArrayList<>(map.values());
    }

    public static void bfs(TreeNode root, Queue<TreeNode> q, Map<Integer, Integer> map) {
        q.offer(root);
        int depth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.remove();
                map.put(depth, node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            depth++;
        }
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
        for (int i :
                rightSideView(root)) {
            System.out.println(i);
        }
    }
}
