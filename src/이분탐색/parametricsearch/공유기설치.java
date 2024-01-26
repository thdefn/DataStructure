package 이분탐색.parametricsearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 가장 가까운/먼 두 객체 사이의 최대/최소 거리 = parametric search
 * 인접한 두 공유기 사이의 거리를 지키며 공유기 C개를 차례로 설치한다
 * <p>
 * 공유기 사이의 거리를 d 이상으로 배치할 때
 * 1. C <= 배치 가능 개수라면 d는 답이 될 수 있다. 더 넓은 거리에 대해 조사한다.
 * 2. C > 배치 가능 개수라면 공유기 사이 거리를 더 좁혀햐 한다.
 */
public class 공유기설치 {
    // O(N)
    static int calculateCount(int[] xs, int distance) {
        int pastX = xs[0];
        int count = 1;
        for (int i = 1; i < xs.length; i++) {
            if (xs[i] - pastX >= distance) {
                pastX = xs[i];
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();
        int[] xs = new int[N];
        for (int i = 0; i < N; i++)
            xs[i] = sc.nextInt();

        Arrays.sort(xs);

        // 1. 공유기 사이의 거리 탐색 범위를 정한다.
        int l = 1;
        int r = xs[N - 1] - xs[0];
        int ans = -1;
        //  O(Log(X)) * O(N) : X는 전체 거리의 범위
        while (l <= r) {
            // 2. 인접한 공유기 사이의 거리에 대해
            //    2-1. 해당 거리를 지키면서 모든 공유기를 설치할 수 있다면 공유기 사이의 거리를 늘려본다.
            //    2-2. 모든 공유기를 설치는 게 불가능하다면 공유기 사이 거리를 좁혀본다.
            int m = (l + r) / 2;
            if (calculateCount(xs, m) >= C) {
                ans = m;
                l = m + 1;
            } else r = m - 1;
        }

        // 3. 가능한 공유기 사이의 최대 거리를 출력한다.
        System.out.println(ans);
    }
}
