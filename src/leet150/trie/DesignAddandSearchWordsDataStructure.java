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
                if (!cur.children.containsKey(word.charAt(i))) {
                    TreeNode child = new TreeNode();
                    cur.children.put(word.charAt(i), child);

                    int size = brunches.size();
                    for (int j = 0; j < size; j++) {
                        TreeNode brunch = brunches.remove();
                        brunch.children.put(word.charAt(i), child);
                        if (!brunch.children.containsKey('.'))
                            brunch.children.put('.', child);
                        else brunches.offer(brunch.children.get('.'));
                    }

                    if (!cur.children.containsKey('.'))
                        cur.children.put('.', child);
                    else brunches.offer(cur.children.get('.'));
                } else brunches.offer(cur.children.get(word.charAt(i)));
                cur = cur.children.get(word.charAt(i));
            }
            cur.isEndOfStr = true;
            for (int i = 0; i < brunches.size(); i++) {
                brunches.remove().isEndOfStr = true;
            }
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
        wordDictionary.addWord("bbbb");
        System.out.println(wordDictionary.search("...."));
        wordDictionary.addWord("wykzbvwdsoyfowqicymzd");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("...."));
        System.out.println(wordDictionary.search("wykzbvwdso..owqicymzd")); // return False
        System.out.println(wordDictionary.search("...")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search(".")); // return True
    }
}
