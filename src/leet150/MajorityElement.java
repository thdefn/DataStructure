package leet150;

import java.util.Arrays;

public class MajorityElement {
    public static void main(String[] args) {
        int nums[] = {3,2,3};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int cnt = 1;
        int answer = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) cnt = 0;
            cnt++;
            if (cnt > nums.length / 2) {
                answer = nums[i];
                break;
            }
        }
        return answer;
    }
}
