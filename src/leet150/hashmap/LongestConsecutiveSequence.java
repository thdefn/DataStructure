package leet150.hashmap;

import java.util.Set;
import java.util.TreeSet;

public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++)
            set.add(nums[i]);

        int before = 0;
        int maxLength = 0;
        int cnt = 1;
        for (int i : set) {
            if (i != before + 1) cnt = 1;
            maxLength = Math.max(cnt++, maxLength);
            before = i;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        System.out.println(longestConsecutive(nums));
    }
}
