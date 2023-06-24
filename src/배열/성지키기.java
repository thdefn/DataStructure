package 배열;

import java.util.Scanner;

public class 성지키기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        char[][] map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = sc.next().toCharArray();
        }

        int existRowCount = 0;
        for (int r = 0; r < N; r++) {
            boolean exist = false;
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 'X') {
                    exist = true;
                    break;
                }
            }

            if (exist) existRowCount++;
        }

        int existColCount = 0;

        for (int c = 0; c < M; c++) {
            boolean exist = false;
            for (int r = 0; r < N; r++) {
                if (map[r][c] == 'X') {
                    exist = true;
                    break;
                }
            }

            if (exist) existColCount++;
        }

        int needRowCount = N - existRowCount;
        int needColCount = M - existColCount;

        System.out.println(Math.max(needRowCount, needColCount));

    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        char[][] map = new char[N][M];

        //O(N * M)
        for (int i = 0; i < N; i++) {
            map[i] = sc.next().toCharArray();
        }

        boolean[] existRow = new boolean[N];
        boolean[] existCol = new boolean[M];

        //O(N * M)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'X') {
                    existRow[i] = true;
                    existCol[i] = true;
                }
            }
        }

        int needRowCount = N;
        int needColCount = M;
        //O(N)
        for (int i = 0; i < N; i++) {
            if(existRow[i]) needRowCount--;
        }
        //O(M)
        for (int i = 0; i < M; i++) {
            if(existCol[i]) needColCount--;
        }

        // //O(N * M) + O(N * M) + O(N) + O(M) ==> O(NM)
        System.out.println(Math.max(needRowCount, needColCount));

    }
}
