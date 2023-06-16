package 배열;

import java.util.Scanner;

public class 성지키기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        String[] strings = new String[N];
        for (int i = 0; i < N; i++) {
            strings[i] = sc.next();
        }
        boolean[] row = new boolean[N];
        boolean[] col = new boolean[M];
        boolean isInRow;
        for (int i = 0; i < N; i++) {
            isInRow = false;
            for (int j = 0; j < M; j++) {
                if (strings[i].charAt(j) != 'X') continue;
                col[j] = true;
                if (!isInRow) isInRow = true;
            }
            row[i] = isInRow;
        }

        int rowCount = 0;
        for (boolean isIn : row) {
            if(!isIn){
                rowCount++;
            }
        }

        int colCount = 0;
        for (boolean isIn : col) {
            if(!isIn){
                colCount++;
            }
        }

        System.out.println(Math.max(rowCount, colCount));
    }
}
