package 배열.풀이;

import java.util.Scanner;

public class 두수의합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cnt = new int[1000001];

        while (n-- > 0) {
            cnt[sc.nextInt()]++;
        }

        int x = sc.nextInt();
        int pairs = 0;

        for (int i = 1; i < 1000001; i++) {
            if (i >= x || i <= x - 1000001) break;
            if (x == 2 * i) continue;
            if (cnt[i] == 1 && cnt[x - i] == 1) {
                pairs++;
                cnt[i] = 0;
                cnt[x - i] = 0;
            }
        }
        System.out.println(pairs);
    }
}
