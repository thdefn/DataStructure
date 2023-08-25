package leet150.twopointers;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] nums = {1,1};
        System.out.println(maxArea(nums));
    }

    public static int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        while (i < j) {
            if (height[i] >= height[j]) {
                max = Math.max(max, height[j] * (j - i));
                j--;
            } else if (height[i] < height[j]){
                max = Math.max(max, height[i] * (j - i));
                i++;
            }
        }
        return max;
    }
}
