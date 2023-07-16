package 완전탐색.풀이;

import java.util.Scanner;

public class 주사위 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int S1 = sc.nextInt();
        int S2 = sc.nextInt();
        int S3 = sc.nextInt();

        int[] cnt = new int[101];
        int maxCnt = 0;
        int maxIdx = 0;
        for (int i = 1; i <= S1; i++) {
            for (int j = 1; j <= S2; j++) {
                for (int k = 1; k <= S3; k++) {
                    int sum = i + j + k;
                    cnt[sum]++;
                    if (cnt[sum] > maxCnt) {
                        maxCnt = cnt[sum];
                        maxIdx = sum;
                    }
                }
            }
        }

        System.out.println(maxIdx);
    }
}
