package leet150.binarysearch;
// 다음 mid 가 mid + 1이 되게끔
// mid + 1 = (다음 left + right) / 2
// 현재 left + x + right = 2mid + 2 x = 2mid + 2 - right  - 현재 left
// mid + 1 = mid + x/2 x = 2

/**
 * mid + 1 = (left + x + right) / 2
 * mid + 1 = mid + x/2
 */

public class FindPeakElement {
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        if(nums.length < 3) return nums[left] > nums[right] ? left : right ;

        while (left < right) {
            int mid = (left + right) / 2;

            if((mid == 0 && nums[mid] > nums[mid + 1]) || (mid == nums.length - 1 && nums[mid] > nums[mid - 1]))
                return mid;
            else if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])
                return mid;
            else if (nums[mid] < nums[mid + 1]) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        System.out.println(findPeakElement(nums));
    }
}
