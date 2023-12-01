package 이분탐색.birarysearch.풀이;

import java.util.Arrays;
import java.util.Scanner;

/**
 * - 이분탐색의 시간복잡도 O(LogN)
 * 처음 탐색 범위 : N
 * 두번째 탐색 범위 : N/2
 * 세번째 탐색 범위 : N/2^2
 * ...
 * LogN번째 탐색 범위 : N/2^LogN = 1 => 탐색 종료
 */
public class 수찾기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Integer[] A = new Integer[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        Arrays.sort(A);
        int M = sc.nextInt();
        int[] numbers = new int[M];
        for (int i = 0; i < M; i++) {
            numbers[i] = sc.nextInt();
        }

        for (int n : numbers) {
            System.out.println(isExist(A, n) ? 1 : 0);
        }

    }

    public static boolean isExist(Integer[] A, int number) {
        int L = 0;
        int R = A.length - 1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (A[mid] > number) R = mid - 1;
            else if (A[mid] < number) L = mid + 1;
            else return true;
        }
        return false;
    }
}
