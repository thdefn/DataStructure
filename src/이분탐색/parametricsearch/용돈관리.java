package 이분탐색.parametricsearch;

import java.util.Scanner;

/**
 * 한번에 k만큼 인출할 때
 * <p>
 * 1. 최소 인출 횟수 <= M : 더 적은 금액이 가능한지 확인
 * 2. 최소 인출 횟수 > M : k보다 더 큰 금액 인출
 */
public class 용돈관리 {
    // O(N)
    static boolean isPossible(int[] useAmounts, int drawAmount, int maxDrawCount) {
        int drawCount = 1;
        int currentAmount = drawAmount;
        for (int useAmount : useAmounts) {
            if (useAmount > drawAmount) return false;
            if (currentAmount < useAmount) {
                if (drawCount == maxDrawCount) return false;
                drawCount++;
                currentAmount = drawAmount;
            }
            currentAmount -= useAmount;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] useAmounts = new int[N];
        for (int i = 0; i < N; i++)
            useAmounts[i] = sc.nextInt();

        // 1. 한번에 인출할 금액의 탐색 범위를 정한다.
        int l = 1;
        int r = N * 10000; // 한번 인출해서 모든 금액을 다 쓸 수 있는게 최댓값
        int ans = -1;
        // O(Log(N*10000)) * O(N)
        while (l <= r) {
            // 2. 임의의 인출 금액에 대해
            //    2-1. 해당 금액으로 M번 이하로 출금할 수 있다면 인출액을 줄여본다.
            //    2-2. M번 이하로 출금하는 게 불가능하다면 인출액을 늘려본다.
            int m = (l + r) / 2;
            if (isPossible(useAmounts, m, M)) {
                r = m - 1;
                ans = m;
            } else l = m + 1;

        }
        // 3. 가능한 인출액 중 최소 금액을 출력한다.
        System.out.println(ans);
    }
}
