package 배열;

import java.util.Arrays;
import java.util.Scanner;

public class 줄세우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int P = sc.nextInt();
        String[][] st = new String[P][20];

        for (int i = 0; i < P; i++) {
            st[i] = sc.nextLine().split(" ");
            Arrays.stream(st[i]).forEach(System.out::println);
        }

        for (int i = 0; i < P; i++) {
            int count = 0;
            int index = 21;
            for (int j = 2; j < 21; j++) {
                for (int k = 1; k < j; k++) {
                    if (Integer.parseInt(st[i][k]) >
                            Integer.parseInt(st[i][j])) {
                        index = k;
                        break;
                    }
                }
                if (index < j) count += (j - index);
            }
            System.out.println(i + 1 + " " + count);
        }
    }
}
