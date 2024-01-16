package 이분탐색.parametricsearch;

import java.util.Scanner;

/**
 * 만들 수 있는 최대 랜선의 길이를 구하는 프로그램
 * <p>
 * 길이가 작을수록 만드는 랜선의 개수 많아짐
 * 랜선의 길이는 최대 100만이고 1만개 까지 있을 수 있음
 */
public class 랜선자르기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        int[] heights = new int[K];
        int minHeight = Integer.MAX_VALUE;
        for (int i = 0; i < K; i++) {
            heights[i] = sc.nextInt();
            minHeight = Math.min(minHeight, heights[i]);
        }

        long l = 0;
        long r = minHeight;
        long answer = minHeight;
        while (l <= r) {
            long m = (l + r) / 2;
            if (isPossible(heights, m, N)) {
                answer = m;
                l = m + 1;
            } else r = m - 1;
        }
        System.out.println(answer);
    }

    public static boolean isPossible(int[] heights, long cuttingHeight, int N) {
        long volume = 0;
        for (int h : heights)
            volume += ((long) h / cuttingHeight);
        return volume >= N;
    }
}
