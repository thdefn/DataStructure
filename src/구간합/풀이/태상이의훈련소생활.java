package 구간합.풀이;

import java.util.Scanner;

public class 태상이의훈련소생활 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] heights = new int[N + 1];
        for (int i = 1; i <= N; i++)
            heights[i] = sc.nextInt();

        int[] commands = new int[N + 2];
        while (M-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int k = sc.nextInt();
            commands[a] = commands[a] + k;
            commands[b + 1] = commands[b + 1] - k;
        }
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += commands[i];
            System.out.print(heights[i] + sum + " ");
        }

    }
}
