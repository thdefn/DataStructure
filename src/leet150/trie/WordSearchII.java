package leet150.trie;

import java.util.*;

public class WordSearchII {
    public static List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        TreeNode root = new TreeNode();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                TreeNode n = new TreeNode();
                if (i > 0) {
                    TreeNode neighbor = root.children.get(board[i - 1][j]).get(String.valueOf(i - 1) + j);
                    // 현재 맵에 이웃을 삽입
                    Map<String, TreeNode> charMap = n.children.getOrDefault(board[i - 1][j], new HashMap<>());
                    charMap.put(String.valueOf(i) + j, neighbor);
                    n.children.put(board[i - 1][j], charMap);
                    // 이전 이웃에 삽입되었던 해시맵 값 변경
                    charMap = neighbor.children.get(board[i][j]);
                    charMap.put(String.valueOf(i) + j, n);
                    neighbor.children.put(board[i][j], charMap);
                }
                if (j > 0) {
                    TreeNode neighbor = root.children.get(board[i][j - 1]).get(String.valueOf(i) + (j - 1));
                    // 현재 맵에 이웃을 삽입
                    Map<String, TreeNode> charMap = n.children.getOrDefault(board[i][j - 1], new HashMap<>());
                    charMap.put(String.valueOf(i) + j, neighbor);
                    n.children.put(board[i][j - 1], charMap);
                    // 이전 이웃에 삽입되었던 해시맵 값 변경
                    charMap = neighbor.children.get(board[i][j]);
                    charMap.put(String.valueOf(i) + j, n);
                    neighbor.children.put(board[i][j], charMap);
                }
                if (i < board.length - 1) {
                    Map<String, TreeNode> charMap = n.children.getOrDefault(board[i + 1][j], new HashMap<>());
                    charMap.put(String.valueOf(i + 1) + j, new TreeNode());
                    n.children.put(board[i + 1][j], charMap);
                }
                if (j < board[i].length - 1) {
                    Map<String, TreeNode> charMap = n.children.getOrDefault(board[i][j + 1], new HashMap<>());
                    charMap.put(String.valueOf(i) + (j + 1), new TreeNode());
                    n.children.put(board[i][j + 1], charMap);
                }

                Map<String, TreeNode> rootMap = root.children.getOrDefault(board[i][j], new HashMap<>());
                rootMap.put(String.valueOf(i) + j, n);
                root.children.put(board[i][j], rootMap);
            }
        }

        List<String> answer = new ArrayList<>();
        for (String w : words) {

            boolean isExisted = true;
            for (int i = 0; i < w.length(); i++) {
                if(!root.children.containsKey(w.charAt(i))){
                    isExisted = false;
                    break;
                }
            }
            if(!isExisted) continue;

            Queue<TreeNode> q = new LinkedList<>();
            Map<TreeNode, Set<TreeNode>> prev = new HashMap<>();
            Map<TreeNode, Set<TreeNode>> temp;
            q.add(root);
            boolean detected = false;
            int depth = 0;
            while (!q.isEmpty()) {
                System.out.println("-----"+ depth);
                //if (depth == w.length()) break;
                int size = q.size();
                temp = new HashMap<>();
                System.out.println(size);
                for (int j = 0; j < size; j++) {
                    TreeNode cur = q.remove();
                    boolean hasChild = false;
                    Set<TreeNode> parentSet = new HashSet<>(prev.getOrDefault(cur, new HashSet<>()));
                    parentSet.add(cur);

                    System.out.println(parentSet.size() + " " + w.charAt(depth));

                    //if (depth == w.length()) break;
                    if(!cur.children.containsKey(w.charAt(depth))) continue;
                    for (TreeNode n : cur.children.get(w.charAt(depth)).values()) {
                        if (!parentSet.contains(n)) {
                            q.offer(n);
                            hasChild = true;
                            temp.put(n, parentSet);
                        }
                    }


                    //System.out.println(parentSet.size() + "" + hasChild);

                    if(parentSet.size() == w.length() && hasChild){
                        answer.add(w);
                        detected = true;
                        break;
                    }
                }

                depth++;
                prev = temp;

                if(detected) break;
            }

        }

        return answer;
    }

    static class TreeNode {
        int i;
        int j;
        Map<Character, Map<String, TreeNode>> children;

        TreeNode() {
            this.children = new HashMap<>();
        }

        TreeNode(int i, int j) {
            this.children = new HashMap<>();
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                {'b', 'a', 'b', 'a'},
                {'b', 'b', 'b', 'a'},
                {'b', 'b', 'b', 'a'},
                {'b', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a'}

        };
        // {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'},{'i', 'f', 'l', 'v'}};
        //{{'o', 'a', 'b', 'n'}, {'o', 't', 'a', 'e'}, {'a', 'h', 'k', 'r'}, {'a', 'f', 'l', 'v'}};
        //{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'},{'i', 'f', 'l', 'v'}};
        String[] word = {"aabaaaaab"};
        for (String s :
                findWords(board, word)) {
            System.out.println(s);
        }
    }
}
