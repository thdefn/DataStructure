package leet150.tree;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageofLevelsinBinaryTree {
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

    public static List<Double> averageOfLevels(TreeNode root) {
        return bfs(root);
    }

    public static List<Double> bfs(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Double> nums = new ArrayList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            double avg = 0.00000d;
            for (int i = 0; i < size; i++) {
                TreeNode n = q.remove();
                avg += n.val;
                if (n.left != null) q.offer(n.left);
                if (n.right != null) q.offer(n.right);
            }
            nums.add(avg/ size);
        }
        return nums;
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        TreeNode right = new TreeNode(9);
        TreeNode root = new TreeNode(3, left, right);
        for (Double d:
             averageOfLevels(root)) {
            System.out.println(d);
        }
    }

}
