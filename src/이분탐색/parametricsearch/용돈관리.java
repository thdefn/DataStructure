package 이분탐색.parametricsearch;

import java.util.Scanner;

public class 용돈관리 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] expenses = new int[N];
        for (int i = 0; i < N; i++)
            expenses[i] = sc.nextInt();

        int l = 1;
        int r = 10000;
        int answer = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            int outCount = getOutCount(expenses, m);
            if (outCount > M){
                l = m + 1;
            } else if (outCount < M){
                r = m - 1;
            } else {
                answer = m;
                r = m - 1;
            }
        }

        System.out.println(answer);
    }

    private static int getOutCount(int[] expenses, int amount) {
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
