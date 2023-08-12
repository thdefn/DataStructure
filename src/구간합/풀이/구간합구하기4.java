package 구간합.풀이;

import java.util.Scanner;

public class 구간합구하기4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = sc.nextInt();

        int[] acc = new int[N + 1];
        for (int i = 1; i <= N; i++)
            acc[i] = acc[i - 1] + arr[i];

        while (M-- > 0) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            System.out.println(acc[e] - acc[s - 1]);
        }
    }
}
