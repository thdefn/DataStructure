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
                if (!cur.children.containsKey(word.charAt(i))) { // j
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

        System.out.println(wordDictionary.search("a"));
        System.out.println(wordDictionary.search(".at"));
        wordDictionary.addWord("bat");
        System.out.println(wordDictionary.search(".at"));
        System.out.println(wordDictionary.search("an."));
        System.out.println(wordDictionary.search("a.d."));
        System.out.println(wordDictionary.search("b."));
        System.out.println(wordDictionary.search("a.d"));
        System.out.println(wordDictionary.search("."));
    }
}
