package leet150.binarysearch;

/**
 * 1 3 5 6
 * 1 3 5 5 6
 */
public class SearchInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        if (nums[nums.length - 1] < target)
            return nums.length;
        int left = 0;
        int right = nums.length - 1;
        int idx = 0;
        while (left < right) {
            idx = (right + left) / 2;
            if (nums[idx] < target) left = idx + 1;
            else right = idx;
        }
        return right;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        System.out.println(searchInsert(nums, 0));
    }
}
