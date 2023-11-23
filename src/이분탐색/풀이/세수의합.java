package 이분탐색.풀이;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * x + y + z = k
 * x + y = k - z
 *
 * x + y 의 모든 경우의 수 구하기
 * k - z 의 모든 경우의 수 구한 후, x + y 로 가능한지 체크
 *
 * 수식을 바꿔 시간복잡도를 줄임
 */
public class 세수의합 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        Set<Integer> sumSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumSet.add(numbers[i] + numbers[j]);
            }
        }

        int maxAnswer = 0;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (sumSet.contains(numbers[i] - numbers[j])) {
                    maxAnswer = Math.max(numbers[i], maxAnswer);
                }
            }
        }
        System.out.println(maxAnswer);
    }
}
