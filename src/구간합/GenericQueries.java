package 구간합;

import java.util.Scanner;

public class GenericQueries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        int[] arr = new int[N + 1];
        for (int i = 0; i < N; i++)
            arr[i + 1] = arr[i] ^ sc.nextInt();

        int answer = 0;
        while (Q-- > 0) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            answer = answer ^ (arr[end] ^ arr[start - 1]);
        }
        System.out.println(answer);
    }
}
