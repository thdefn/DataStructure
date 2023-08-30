package leet150.hashmap;

import java.util.HashMap;
import java.util.Map;


public class RansomNote {
    public static boolean canConstruct2(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++)
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);

        for (int i = 0; i < ransomNote.length(); i++) {
            if (map.getOrDefault(ransomNote.charAt(i), 0) < 1) return false;
            map.put(ransomNote.charAt(i), map.get(ransomNote.charAt(i)) - 1);
        }
        return true;
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] cnt = new int['z' - 'a' + 1];
        for (int i = 0; i < magazine.length(); i++)
            cnt[magazine.charAt(i) - 'a']++;

        for (int i = 0; i < ransomNote.length(); i++) {
            int charIdx = ransomNote.charAt(i) - 'a';
            if (cnt[charIdx] < 1) return false;
            cnt[charIdx]--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("a", "b"));
    }
}
