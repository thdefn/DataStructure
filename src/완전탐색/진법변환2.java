package 완전탐색;

import java.util.Scanner;

public class 진법변환2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int B = sc.nextInt();

        String ans = "";
        // 1. N을 B로 나눈 나머지를 구하고, B로 나누자
        // 2. 이때 가장 마지막 나머지가 가장 앞 자릿값이다
        while (N > 0) {
            int D = N % B;
            N = N / B;
            if (D < 10) ans += D;
            else ans += (char) (D - 10 + 'A');
        }

        for (int i = ans.length() - 1; i >= 0; i--)
            System.out.print(ans.charAt(i));
        System.out.println();
    }
}
