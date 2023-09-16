package leet150.graph;

import java.util.*;

public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Queue<Node> q = new LinkedList<>();
        q.offer(node);

        Node[] nodes = new Node[101];
        boolean[] isVisited = new boolean[101];

        while (!q.isEmpty()) {
            Node cur = q.remove();
            if(isVisited[cur.val]) continue;
            else if (nodes[cur.val] == null) nodes[cur.val] = new Node(cur.val);

            for (Node n : cur.neighbors) {
                if (nodes[n.val] == null) nodes[n.val] = new Node(n.val);
                nodes[cur.val].neighbors.add(nodes[n.val]);

                if (!isVisited[n.val]) q.offer(n);
            }

            isVisited[cur.val] = true;
        }

        return nodes[1];
    }
}
