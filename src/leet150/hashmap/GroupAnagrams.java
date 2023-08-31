package leet150.hashmap;

import java.util.*;

/**
 */
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            String eigen = Arrays.toString(temp);
            List<String> li = map.getOrDefault(eigen, new ArrayList<>());
            li.add(strs[i]);
            map.put(eigen, li);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"cod", "arc"};
        for (List<String> anas :
                groupAnagrams(strs)) {
            for (String s : anas) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
