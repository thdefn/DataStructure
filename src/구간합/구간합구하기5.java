package 구간합;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class 구간합구하기5 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] arr = new int[N + 1][N + 1];
        int idxX = 1;
        int idxY = 1;
        int sum = 0;
        for (int i = 0; i < N * N; i++) {
            sum = sum + sc.nextInt();
            arr[idxX][idxY++] = sum;
            if (idxY == N + 1) {
                idxX++;
                idxY = 1;
                sum = 0;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < M; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            int answer = 0;
            for (int j = x1; j <= x2; j++) {
                answer += arr[j][y2] - arr[j][y1-1];
            }
            bw.write(answer+"\n");
        }
        bw.flush();
    }
}
