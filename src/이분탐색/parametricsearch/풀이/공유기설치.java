package 이분탐색.parametricsearch.풀이;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 가장 가까운/먼 두 객체 사이의 최대/최소 거리 = parametric search
 *
 * 가장 인접한 두 공유기 사이의 최대 거리
 * 1. 가장 인접한 거리 값 minDistance 를 정한다.
 * 2. 가능한지 판단 -> 가장 인접한 거리 값을 지키며 공유기 C개 배치하기
 * 3-1. 가능하다면 minDistance 를 늘린다.
 * 3-2. 불가능하다면 minDistance 줄임
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
            if (isPossible(x, m, C)) {
                ans = m;
                l = m + 1;
            } else r = m - 1;
        }
        System.out.println(ans);
    }

    private static boolean isPossible(int[] x, long minDistance, int C) {
        int count = C;
        int prevValue = x[0];
        count--;
        for (int i = 1; i < x.length; i++) {
            if (x[i] - prevValue >= minDistance) {
                // 공유기를 현재 좌표에 놓는다.
                count--;
                prevValue = x[i];
            }
            if (count == 0) return true;
        }
        return false;
    }
}
