package 배열;

import java.util.Scanner;

public class 두수의합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int X = sc.nextInt();

        boolean[] exist = new boolean[1000001];
        for (int i = 0; i < n; i++) {
            exist[a[i]] = true;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int pairValue = X - a[i];
            if (pairValue >= 0 && pairValue <= 1000000)
                ans += exist[pairValue] ? 1 : 0;
        }
        // 중복값 발생에 대한 처리
        System.out.println(ans / 2);
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] exist = new boolean[1000001];
        for (int i = 0; i < n; i++) {
            exist[sc.nextInt()] = true;
        }
        int X = sc.nextInt();

        int ans = 0;
        for (int i = 1; i <= (X - 1) / 2; i++) {
            if (i <= 1000000 && X - i <= 1000000)
                ans += (exist[i] && exist[X - i]) ? 1 : 0;
        }
        // 중복값 발생에 대한 처리
        System.out.println(ans);
    }
}
