package 구간합;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 2차원 누적합 배열
 *
 * 1. 필요한 범위를 모두 포함하는 구간합에서
 * 2. 불필요한 범위의 구간합을 모두 배제
 * 3. 중복으로 제거된 구간합 복원
 *
 * 2차원 배열에서의 구간합은 인덱스를 구하는 방법이 헷갈릴 수 있으므로, 종이와 펜으로 그리면서 확인하기
 */
public class 구간합구하기5 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                arr[i][j] = sc.nextInt();

        // 1. 누적합 배열을 구한다
        int[][] acc = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                // 1. 필요한 누적합 참조
                // 2. 중복해서 가져온 누적합을 빼준다
                // 3. 이번 원소를 더한다
                acc[i][j] = acc[i - 1][j] + acc[i][j - 1] - acc[i - 1][j - 1] + arr[i][j];

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (M-- > 0){
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            // 2. 누적합 배열을 사용해 구간합을 구한다.
            // 2-1. 필요한 값이 모두 들어있는 누적합을 참조한다. (x2, y2)
            // 2-2. 불필요한 값이 들어있는 누적합을 빼준다. (x1-1, y2) (x2, y1-1)
            // 2-3. 중복해서 제외된 값을 다시 더해준다. (x1-1, y1-1)
            bw.write(acc[x2][y2] - acc[x1 - 1][y2] - acc[x2][y1 - 1] + acc[x1 - 1][y1 - 1] + "\n");
        }
        bw.flush();
    }
}
