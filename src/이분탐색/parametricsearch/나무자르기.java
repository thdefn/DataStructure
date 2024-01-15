package 이분탐색.parametricsearch;

import java.util.Scanner;

/**
 * M미터 이상의 나무를 가져갈 수 있도록 절단기 높이를 맞출 때, 그 높이의 최댓값
 * <p>
 * 어떤 조건을 만족하는 최댓값 or 최솟값 -> parametric search
 * O(Log(H)) * O(N) = 30 * 1000000 = 30000000 = 3000만
 */
public class 나무자르기 {
    // O(N)
    private static boolean isPossible(int[] heights, int cutH, int M) {
        long sum = 0; // sum 은 잘려나가는 나무의 합이기 때문에 long 타입
        for (int h : heights)
            if (h > cutH) sum += h - cutH;
        return sum >= M;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] h = new int[N];
        for (int i = 0; i < N; i++)
            h[i] = sc.nextInt();

        // 1. 절단기 높이의 탐색 범위를 정한다.
        int l = 0, r = 1000000000, ans = -1;
        // O(Log(H)) * O(N)
        while (l <= r) {
            // 2. 임의의 절단기 높이에 대해
            //    2-1. 원하는 만큼 나무를 가져갈 수 있다면 높이를 높여본다.
            //    2-2. 원하는 만큼 나무를 가져갈 수 없다면 높이를 낮춰준다.
            int m = (l + r) / 2;
            if (isPossible(h, m, M)) {
                ans = m;
                l = m + 1;
            }
            else r = m - 1;
        }
        // 3. 원하는 만큼 가져갈 수 있었던 높이 중 최댓값을 출력한다.
        System.out.println(ans);
    }
}
