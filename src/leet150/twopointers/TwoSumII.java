package leet150.twopointers;

public class TwoSumII {
    public static void main(String[] args) {
        int[] nums = {-1, 0};
        int target = -1;
    }

    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        int[] answer = new int[2];
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                answer[0] = i + 1;
                answer[1] = j + 1;
                break;
            } else if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return answer;
    }
}
