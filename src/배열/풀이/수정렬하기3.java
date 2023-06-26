package 배열.풀이;

import java.util.Scanner;

public class 수정렬하기3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        int[] outs = new int[N];
        for (int i = 0; i < N; i++) {
            boolean max = true;
            for (int j = 0; j < i; j++) {
                if (outs[j] > numbers[i]) {
                    for (int k = i - 1; k >= j; k--) {
                        outs[k + 1] = outs[k];
                    }
                    outs[j] = numbers[i];
                    max = false;
                    break;
                }
            }
            if (max) outs[i] = numbers[i];
        }

        for (int i = 0; i < N; i++) {
            System.out.println(outs[i]);
        }
    }
}
