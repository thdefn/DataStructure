package leet150.twopointers;

import java.util.*;

/**
 * -1 -1 -4 0 1 2
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        threeSum(nums)
                .forEach(a -> a.forEach(System.out::println));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        return answer;
    }
}
