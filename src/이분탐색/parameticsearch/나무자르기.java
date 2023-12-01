package 이분탐색.parameticsearch;

import java.util.Arrays;
import java.util.Scanner;

public class 나무자르기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int maxHeight = -1;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            maxHeight = Math.max(maxHeight, arr[i]);
        }

        int l = 0;
        int r = maxHeight;
        int ans = 0;
        while (l <= r) {
            int m = (l + r) / 2;
            long sum = getCuttingSum(arr, m);
            if (sum >= M) {
                ans = m;
                l = m + 1;
            } else r = m - 1;
        }
        System.out.println(ans);
    }

    private static long getCuttingSum(int[] arr, int h) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] > h)
                sum += ((long) arr[i] - h);
        return sum;
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int maxHeight = -1;
        int[] arr = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = sc.nextInt();
            maxHeight = Math.max(maxHeight, arr[i]);
        }

        Arrays.sort(arr); // N * Log(N)
        long[] acc = new long[N + 1];
        for (int i = 1; i < N + 1; i++) {
            acc[i] = arr[i] + acc[i - 1];
        }

        int l = 0;
        int r = maxHeight;
        int ans = 0;
        // Log(N)*Log(N)
        while (l <= r) {
            int m = (l + r) / 2;
            long sum = calcCuttingSum(arr, acc, m); // Log(N)
            if (sum >= M) {
                ans = m;
                l = m + 1;
            } else r = m - 1;
        }
        System.out.println(ans);
    }

    private static long calcCuttingSum(int[] arr, long[] acc, int h) {
        int lowerBoundIndex = findLowerBoundIndex(arr, h);
        long sumOfTrees = acc[arr.length - 1] - acc[lowerBoundIndex - 1];
        int countOfTrees = arr.length - lowerBoundIndex;
        return sumOfTrees - (long) countOfTrees * h;
    }

    // h 보다 크거나 같은 최소값의 인덱스
    private static int findLowerBoundIndex(int[] arr, int h) {
        int l = 1;
        int r = arr.length - 1;
        int lowerBoundIndex = arr.length;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] >= h) {
                lowerBoundIndex = m;
                r = m - 1;
            } else l = m + 1;
        }
        return lowerBoundIndex;
    }
}
