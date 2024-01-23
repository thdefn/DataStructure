package 이분탐색.parametricsearch.풀이;

import java.util.Scanner;

/**
 * 남은 금액이 그날 사용할 금액보다 많더라도 남은 금액은 통장에 집어넣고 다시 K원을 인출할 수 있다.
 * == 남은 금액을 사용할 수 있더라도 다시 금액 인출이 가능함
 *
 * - 이분 탐색에서 범위 잘 확인하기
 */
public class 용돈관리 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] expenses = new int[N];
        int maxExpense = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            expenses[i] = sc.nextInt();
            maxExpense = Math.max(maxExpense, expenses[i]);
        }

        int l = maxExpense;
        int r = N * 10000;
        int answer = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            int outCount = getMinOutCount(expenses, m);
            if (outCount > M) {
                l = m + 1;
            } else {
                answer = m;
                r = m - 1;
            }
        }

        System.out.println(answer);
    }

    private static int getMinOutCount(int[] expenses, int amount) {
        int balance = amount;
        int outCount = 1;
        for (int e : expenses) {
            if (e > balance) {
                balance = amount;
                outCount++;
            }
            balance -= e;
        }
        return outCount;
    }
}
