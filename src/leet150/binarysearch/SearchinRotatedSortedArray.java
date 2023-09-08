package leet150.binarysearch;

/**
 * [5,6,7,0,1,2,3,4]
 * 0 1 2 3 4 5 6 7
 * <p>
 * 7
 */
public class SearchinRotatedSortedArray {
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            System.out.println(left + " " + right);

            if (nums[mid] == target) return mid;
            else if (mid == 0 || target < nums[mid] && nums[mid - 1] > nums[mid + 1])
                left = mid + 1;
            else if (target > nums[mid] && nums[mid - 1] > nums[mid + 1])
                right = mid;
            else if (target > nums[mid] && nums[mid - 1] < nums[mid + 1])
                left = mid + 1;
            else if (target < nums[mid] && nums[mid - 1] < nums[mid + 1])
                right = mid;
        }
        return (nums[left] == target) ? left : -1 ;
    }

    public static void main(String[] args) {
        int nums[] = {3, 5, 1};
        System.out.println(search(nums, 3));
    }
}
