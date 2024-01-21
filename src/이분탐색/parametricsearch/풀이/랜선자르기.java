package 이분탐색.parametricsearch.풀이;

import java.util.Scanner;

/**
 * 만들 수 있는 최대 랜선의 길이를 구하는 프로그램
 * <p>
 * 길이가 작을수록 만드는 랜선의 개수 많아짐
 */
public class 랜선자르기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        int[] heights = new int[K];
        int maxHeight = -1;
        for (int i = 0; i < K; i++) {
            heights[i] = sc.nextInt();
            maxHeight = Math.max(maxHeight, heights[i]);
        }

        // 답이 될 수 있는 최대 길이는 maxHeight
        long l = 1;
        long r = maxHeight;
        long answer = maxHeight;
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
            volume += (h / cuttingHeight);
        return volume >= N;
    }
}
