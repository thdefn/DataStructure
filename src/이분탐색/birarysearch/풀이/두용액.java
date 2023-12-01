package 이분탐색.birarysearch.풀이;

import java.util.Arrays;
import java.util.Scanner;

// -99 -2 -1 4 98
public class 두용액 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);
        int[] answer = new int[2];
        int minAbsOfSum = Integer.MAX_VALUE;
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (Math.abs(sum) < minAbsOfSum) {
                minAbsOfSum = Math.abs(sum);
                answer[0] = numbers[l];
                answer[1] = numbers[r];
            }

            if (sum < 0) {
                l++;
            } else if (sum > 0) {
                r--;
            } else {
                break;
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}
