package leet150.array;

/**
 * 0 1 2 3 4
 * 2 3 1 1 4
 * 2 4 3 4
 * t t t t t
 * <p>
 * 0 1 2 3 4
 * 3 2 1 0 4
 * 3 3 3 3
 * t t t t f
 */
public class JumpGame {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] nums) {
        int maxJumpIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxJumpIndex) return false;
            if (nums[i] + i > maxJumpIndex) maxJumpIndex = nums[i] + i;
        }
        return true;
    }
}
