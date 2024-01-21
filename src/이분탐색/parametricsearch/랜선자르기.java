package 이분탐색.parametricsearch;

import java.util.Scanner;

/**
 * N개의 랜선을 만들 때, 이 랜선의 길이의 최대값
 * <p>
 * 만든 랜선의 개수를 k 라고 할 때
 * 1. k < N : 랜선의 길이를 줄인다
 * 2. k >= N : 더 긴 길이의 랜선이 가능한지 살펴본다.
 */
public class 랜선자르기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        int N = sc.nextInt();
        int[] len = new int[K];
        for (int i = 0; i < K; i++)
            len[i] = sc.nextInt();

        // 1. 만들어볼 랜선 길이의 탐색 범위를 정한다.
        long l = 1;
        long r = (1L << 31) - 1; // 2^31 - 1
        long ans = -1;

        // O(마og(L)) * O(K) -> L은 가능한 랜선의 길이
        while (l <= r) {
            // 2. 임의의 랜선 길이에 대해
            //    2-1. 해당 길이의 랜선을 N개 이상 만들 수 있다면 길이를 늘려본다.
            //    2-2. N개 이상 만들 수 없다면 길이를 줄여본다.
            long m = (l + r) / 2;
            if (calculateCount(len, m) >= N) {
                ans = m;
                l = m + 1;
            } else r = m - 1;
        }
        // 3. N개 이상 만들 수 있었던 길이 중 최댓값을 출력한다.
        System.out.println(ans);
    }

    // O(K)
    static int calculateCount(int[] lens, long cutLength) {
        int cnt = 0;
        for (int len : lens)
            cnt += len / cutLength;
        return cnt;
    }
}
