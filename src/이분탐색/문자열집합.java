package 이분탐색;

import java.util.Arrays;
import java.util.Scanner;

/**
 * O(M * L * Log(N))
 * L은 M에 속하는 문자열의 길이
 */
public class 문자열집합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        String[] arr = new String[N];
        for (int i = 0; i < N; i++)
            arr[i] = sc.next();

        // 1. Binary Search 를 적용하기 위해 정렬한다.
        Arrays.sort(arr);

        int count = 0;
        // O(M*|x|*Log(N))
        while (M-- > 0) {
            String x = sc.next();
            // 2. arr 에 x가 있는지 확인한다.
            if (isExist(arr, x))
                count++;
        }
        System.out.println(count);
    }

    // O(|x|*Log(N))
    private static boolean isExist(String[] arr, String x) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            int compareResult = arr[m].compareTo(x);
            if (compareResult < 0)
                l = m + 1;
            else if (compareResult > 0)
                r = m - 1;
            else return true;
        }
        return false;
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        String[] arr = new String[N];
        for (int i = 0; i < N; i++)
            arr[i] = sc.next();

        // 1. Binary Search 를 적용하기 위해 정렬한다.
        Arrays.sort(arr);

        int count = 0;
        while (M-- > 0) {
            String x = sc.next();
            // 2. arr 에 x가 있는지 확인한다.
            if (Arrays.binarySearch(arr, x) >= 0)
                count++;
        }
        System.out.println(count);
    }

}
