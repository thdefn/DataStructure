package 완전탐색.풀이;

import java.util.Scanner;

public class 사탕게임 {
    static int N;

    public static void swapCandy(char[][] c, int r1, int c1, int r2, int c2) {
        char temp = c[r1][c1];
        c[r1][c1] = c[r2][c2];
        c[r2][c2] = temp;
    }

    public static int findMaxCandy(char[][] c) {
        int max = 0;
        for (int i = 0; i < N; i++) {
            char before = 0;
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (c[i][j] != before) {
                    before = c[i][j];
                    if (cnt > max) max = cnt;
                    cnt = 1;
                } else cnt++;
            }
            if (cnt > max) max = cnt;
        }


        for (int i = 0; i < N; i++) {
            char before = 0;
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (c[j][i] != before) {
                    before = c[j][i];
                    if (cnt > max) max = cnt;
                    cnt = 1;
                } else cnt++;
            }
            if (cnt > max) max = cnt;
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        char[][] candies = new char[N][N];
        for (int i = 0; i < N; i++) {
            candies[i] = sc.next().toCharArray();
        }

        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i + 1 < N && candies[i][j] != candies[i + 1][j]) {
                    swapCandy(candies, i, j, i + 1, j);
                    max = Math.max(findMaxCandy(candies), max);
                    swapCandy(candies, i, j, i + 1, j);
                }

                if (j + 1 < N && candies[i][j] != candies[i][j + 1]) {
                    swapCandy(candies, i, j, i, j + 1);
                    max = Math.max(findMaxCandy(candies), max);
                    swapCandy(candies, i, j, i, j + 1);
                }
            }
        }

        System.out.println(max);
    }
}
