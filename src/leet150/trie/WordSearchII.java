package leet150.trie;

import java.util.*;

public class WordSearchII {
    public static List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        TreeNode root = new TreeNode();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                TreeNode node = new TreeNode();
                if (i > 0) {
                    TreeNode neighbor = root.children.get(board[i - 1][j]).get(String.valueOf(i - 1) + j);
                    // 현재 맵에 이웃을 삽입
                    Map<String, TreeNode> charMap = node.children.getOrDefault(board[i - 1][j], new HashMap<>());
                    charMap.put(String.valueOf(i - 1) + j, neighbor);
                    node.children.put(board[i - 1][j], charMap);
                    // 이전 이웃에 삽입되었던 해시맵 값 변경
                    charMap = neighbor.children.get(board[i][j]);
                    charMap.put(String.valueOf(i) + j, node);
                    neighbor.children.put(board[i][j], charMap);
                }
                if (j > 0) {
                    TreeNode neighbor = root.children.get(board[i][j - 1]).get(String.valueOf(i) + (j - 1));
                    // 현재 맵에 이웃을 삽입
                    Map<String, TreeNode> charMap = node.children.getOrDefault(board[i][j - 1], new HashMap<>());
                    charMap.put(String.valueOf(i) + (j - 1), neighbor);
                    node.children.put(board[i][j - 1], charMap);
                    // 이전 이웃에 삽입되었던 해시맵 값 변경
                    charMap = neighbor.children.get(board[i][j]);
                    charMap.put(String.valueOf(i) + j, node);
                    neighbor.children.put(board[i][j], charMap);
                }
                if (i < board.length - 1) {
                    Map<String, TreeNode> charMap = node.children.getOrDefault(board[i + 1][j], new HashMap<>());
                    charMap.put(String.valueOf(i + 1) + j, new TreeNode());
                    node.children.put(board[i + 1][j], charMap);
                }
                if (j < board[i].length - 1) {
                    Map<String, TreeNode> charMap = node.children.getOrDefault(board[i][j + 1], new HashMap<>());
                    charMap.put(String.valueOf(i) + (j + 1), new TreeNode());
                    node.children.put(board[i][j + 1], charMap);
                }

                Map<String, TreeNode> rootMap = root.children.getOrDefault(board[i][j], new HashMap<>());
                rootMap.put(String.valueOf(i) + j, node);
                root.children.put(board[i][j], rootMap);
            }
        }

        List<String> answer = new ArrayList<>();
        for (String w : words) {
            if(isExistedInNode(root, w, new HashSet<>())) answer.add(w);
        }

        return answer;
    }

    private static boolean isExistedInNode(TreeNode node, String w, Set<TreeNode> parents){
        if(w.length() == 0) return true;
        if(!node.children.containsKey(w.charAt(0)))
            return false;
        parents.add(node);
        for (TreeNode next : node.children.get(w.charAt(0)).values()) {
            if(parents.contains(next)) continue;
            if(isExistedInNode(next, w.substring(1), parents)) return true;
        }
        parents.remove(node);
        return false;
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
       // System.out.println("abc".substring(1));
        char[][] board2 = {
                {'b', 'a', 'b', 'a'},
                {'b', 'b', 'b', 'a'},
                {'b', 'b', 'b', 'a'},
                {'b', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a'}

        };

        char[][] board = {
                {'a', 'a'}

        };
        // {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'},{'i', 'f', 'l', 'v'}};
        //{{'o', 'a', 'b', 'n'}, {'o', 't', 'a', 'e'}, {'a', 'h', 'k', 'r'}, {'a', 'f', 'l', 'v'}};
        //{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'},{'i', 'f', 'l', 'v'}};
        String[] word = {"aaa"};
        for (String s :
                findWords(board, word)) {
            System.out.println(s);
        }
    }
}
