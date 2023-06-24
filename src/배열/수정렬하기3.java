package 배열;

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
        int min = 10001;
        for (int i = 0; i < N; i++) {
            if(numbers[i] < min) min = numbers[i];
        }

    }
}
