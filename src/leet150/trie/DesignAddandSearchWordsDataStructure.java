package leet150.trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 */
public class DesignAddandSearchWordsDataStructure {
    static class WordDictionary {
        TreeNode root;

        public WordDictionary() {
            root = new TreeNode();
        }

        public void addWord(String word) {
            TreeNode cur = root;
            Queue<TreeNode> brunches = new LinkedList<>();
            for (int i = 0; i < word.length(); i++) {
                // 현재 노드 자식 노드로 철자가 없음
                if (!cur.children.containsKey(word.charAt(i))) {
                    brunches.offer(cur);

                    TreeNode child = new TreeNode();
                    int size = brunches.size();
                    for (int j = 0; j < size; j++) {
                        TreeNode brunch = brunches.remove();
                        brunch.children.put(word.charAt(i), child);
                        if (!brunch.children.containsKey('.'))
                            brunch.children.put('.', child);
                        else brunches.offer(brunch.children.get('.'));
                    }
                } else {
                    // 현재 노드 자식 노드로 철자가 있음
                    brunches.offer(cur.children.get(word.charAt(i)));
                    brunches.offer(cur.children.get('.'));
                }
                cur = cur.children.get(word.charAt(i));
            }
            cur.isEndOfStr = true;
        }

        public boolean search(String word) {
            TreeNode cur = root;
            for (char c : word.toCharArray()) {
                if (!cur.children.containsKey(c)) return false;
                cur = cur.children.get(c);
            }
            return cur.isEndOfStr;
        }

        static class TreeNode {
            Map<Character, TreeNode> children;
            boolean isEndOfStr = false;

            public TreeNode() {
                children = new HashMap<>();
            }

        }
    }

    public static void main(String[] args) {

        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad"));
        System.out.println(wordDictionary.search("bad"));
        System.out.println(wordDictionary.search(".ad"));
        System.out.println(wordDictionary.search("b.."));

        WordDictionary wd = new WordDictionary();
        String str = "";
        for (int i = 0; i < 27; i++) {
            str += (char) (i + 'a');
            wd.addWord(str);

            String search = "";
            for (int j = 0; j < str.length(); j++) {
                search += ".";
            }
            System.out.println(wd.search(str) + " " + wd.search(search));
        }

        str = "";
        System.out.println("다시 서치");
        for (int i = 0; i < 27; i++) {
            str += (char) (i + 'a');
            String search = "";
            for (int j = 0; j < str.length(); j++) {
                search += ".";
            }
            System.out.println(wd.search(str) + " " + wd.search(search));
        }

    }
}
