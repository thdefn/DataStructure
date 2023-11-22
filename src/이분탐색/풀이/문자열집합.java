package 이분탐색.풀이;

import java.util.Arrays;
import java.util.Scanner;

public class 문자열집합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        String[] S = new String[N];
        for (int i = 0; i < N; i++) {
            S[i] = sc.next();
        }

        Arrays.sort(S);

        String[] strings = new String[M];
        for (int i = 0; i < M; i++) {
            strings[i] = sc.next();
        }


        int sum = 0;
        for (String string : strings) {
            if (isExisted(S, string)) sum++;
        }
        System.out.println(sum);
    }

    private static boolean isExisted(String[] S, String string) {
        int l = 0;
        int r = S.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            int compareInt = S[m].compareTo(string);
            if (compareInt < 0)
                l = m + 1;
            else if (compareInt > 0)
                r = m - 1;
            else return true;
        }
        return false;
    }
}
