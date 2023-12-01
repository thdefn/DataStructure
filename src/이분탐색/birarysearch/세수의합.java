package 이분탐색.birarysearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * O(N^2*Log(N))
 * 식을 변형하여 세 수의 조합이 아닌 각 두 수를 조합하는 형태로 시간복잡도를 줄인다
 * <p>
 * i=0 N개 i=1 N-1개 ... i=N-2 2개 i=N-1 1개
 * (수열의 합) N + N -1 + ... 2 + 1 = N * (N + 1) / 2
 */
public class 세수의합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();

        // A + B + C = X -> A + B = X - C
        // 1. A + B의 집합을 만든다.
        int[] sums = new int[N * (N + 1) / 2];
        int sumsIndex = 0;
        for (int i = 0; i < N; i++)
            for (int j = i; j < N; j++)
                sums[sumsIndex++] = arr[i] + arr[j];

        Arrays.sort(sums);
        int ans = -1;
        // 2. 모든 X - C 에 대해 A + B 집합에 존재하는지 확인한다.
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) { // 차이기 때문에 순서에 따라 결과가 달라짐
                int target = arr[i] - arr[j];
                if (isExist(sums, target))
                    ans = Math.max(ans, arr[i]);
            }

        // 3. A + B = X - C 을 만족하는 X 중 최대값을 출력한다.
        System.out.println(ans);
    }

    private static boolean isExist(int[] sums, int target) {
        int l = 0;
        int r = sums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (sums[m] < target)
                l = m + 1;
            else if (sums[m] > target)
                r = m - 1;
            else return true;
        }
        return false;
    }

    // (참고) O(N^3) 세 수의 조합을 구하는 형태의 풀이
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Set<Integer> set = new HashSet<>();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            set.add(arr[i]);
        }

        int ans = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    int sum = arr[i] + arr[j] + arr[k];
                    if (set.contains(sum))
                        ans = Math.max(ans, sum);
                }
            }
        }

        System.out.println(ans);
    }
}
