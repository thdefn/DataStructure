package leet150.array;

public class RemoveDuplicatesfromSortedArray2 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        // idx 0에 대한 처리
        int k = 1;
        int cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) cnt = 0;
            if(cnt == 2) continue;
            cnt++;
            nums[k++] = nums[i];
        }
        return k;
    }
}
