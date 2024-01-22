package 이분탐색.parametricsearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1 2 4 8 9
 */
public class 공유기설치 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();
        int[] x = new int[N];
        for (int i = 0; i < N; i++)
            x[i] = sc.nextInt();

        Arrays.sort(x);
        long l = 1;
        long r = x[N - 1] - x[0];
        long ans = -1;
        while (l <= r) {
            long m = (l + r) / 2;
            System.out.println(m);
            if (isPossible(x, m, C)) {
                ans = m;
                l = m + 1;
            } else r = m - 1;
        }
        System.out.println(ans);
    }

    private static boolean isPossible(int[] x, long distance, int C) {
        long target = x[0] + distance;
        int l = 0;
        int r = x.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (x[m] == target) {
                if (x.length - m >= C) return true;
                else return false;
            } else if (x[m] > target)
                r = m - 1;
            else l = m + 1;
        }
        return false;
    }
}
