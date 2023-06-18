package 배열;

import java.util.Arrays;
import java.util.Scanner;

public class 줄세우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int P = sc.nextInt();
        int[][] st = new int[P][20];

        for (int i = 0; i < P; i++) {
            for (int j = 0; j < 20; j++) {
                st[i][j] = sc.nextInt();
            }
            Arrays.stream(st[i]).forEach(System.out::println);
        }

        for (int i = 0; i < P; i++) {
            int[] arr = st[i];
            int count = 0;
            int index = 21;
            for (int j = 2; j < 21; j++) {
                for (int k = 1; k < j; k++) {
                    if(arr[k] > arr[j]) {
                        index = k;
                        break;
                    }
                }
                if(index < j) count += (j-index);
            }
            System.out.println(i+1 + " " + count);
        }
    }
}
