package 이분탐색.parameticsearch;

import java.util.Scanner;

/**
 * O(Log(n))
 * 1 << 32
 * 1000...00 = 2^32
 *
 * (0 ≤ n < 2^63)
 */
public class 정수제곱근 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long l = 0;
        long r = 1L << 32;
        long sqrt = -1;
        while (l <= r){
            long m = (l + r) / 2;
            if(isIntegerSqrt(n, m)) {
                r = m - 1;
                sqrt = m;
            }
            else l = m + 1;
        }
        System.out.println(sqrt);
    }

    private static boolean isIntegerSqrt(long n, long q){
        return q * q >= n;
    }
}
