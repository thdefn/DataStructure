package leet150.trie;

public class ImplementTrie {
    static class Trie {
        static class TreeNode {
            TreeNode[] childNodes;
            boolean isEndOfStr = false;

            public TreeNode() {
                childNodes = new TreeNode['z' - 'a' + 1];
            }
        }

        TreeNode root;

        public Trie() {
            root = new TreeNode();
        }

        public void insert(String word) {
            TreeNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.childNodes[c - 'a'] == null)
                    cur.childNodes[c - 'a'] = new TreeNode();
                cur = cur.childNodes[c - 'a'];
            }
            cur.isEndOfStr = true;
        }

        public boolean search(String word) {
            TreeNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.childNodes[c - 'a'] == null)
                    return false;
                cur = cur.childNodes[c - 'a'];
            }
            return cur.isEndOfStr;
        }

        public boolean startsWith(String prefix) {
            TreeNode cur = root;
            for (char c : prefix.toCharArray()) {
                if (cur.childNodes[c - 'a'] == null)
                    return false;
                cur = cur.childNodes[c - 'a'];
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // return True
        System.out.println(trie.search("app"));     // return False
        System.out.println(trie.startsWith("app")); // return True
        trie.insert("app");
        System.out.println(trie.search("app"));     // return True
    }
}
