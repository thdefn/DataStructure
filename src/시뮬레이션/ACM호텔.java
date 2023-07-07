package 시뮬레이션;

import java.util.Scanner;

public class ACM호텔 {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int H = sc.nextInt();
            int W = sc.nextInt();
            int N = sc.nextInt();

            int floor;
            int number;
            if (H == 1) {
                floor = 1;
                number = N;
            }
            else if (W == 1) {
                floor = N;
                number = 1;
            }
            else {
                floor = (N % H == 0) ? H : N % H;
                number = (N % H == 0) ? N / H : N / H + 1;
            }
            System.out.printf("%d%02d", floor, number);
        }
    }

    public static void main(String[] args) {
        int H = 1;
        int W = 99;
        for (int i = 1; i <= H*W ; i++) {
            int floor;
            int number;
            if (H == 1) {
                floor = 1;
                number = i;
            }
            else if (W == 1) {
                floor = i;
                number = 1;
            }
            else {
                floor = (i % H == 0) ? H : i % H;
                number = (i % H == 0) ? i / H : i / H + 1;
            }
            System.out.printf("%d%02d", floor, number);
            System.out.println();
        }
    }
}
