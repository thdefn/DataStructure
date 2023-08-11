package 구간합;

import java.util.Scanner;

/**
 * XOR 계산 :
 * 1) 0 ^ 0 = 0
 * 2) 1 ^ 1 = 0
 * 3) 0 ^ 1 = 1
 * 4) 1 ^ 0 = 1
 * <p>
 * 0은 XOR 계산에서 항등원이다. -> 1) 4)
 * 따라서 a ^ b ^ b = a
 * <p>
 * 누적합 배열은 구간합 연산뿐만 아니라 [l, r] 구간의 연산 결과를 구하기 위해
 * [1, r] 연산에서 [1, l-1] 구간의 연산을 제거하는 복원작업이 가능한 다른 종류의 연산에도 적용할 수 있습니다.
 * XOR(arr[l], arr[r]) = acc[r] ^ acc[l-1]
 * MULT(arr[l], arr[r]) = acc[r] / acc[l-1]
 */
public class GenericQueries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int Q = sc.nextInt();
        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = sc.nextInt();

        // 1. 누적 XOR 배열을 구한다.
        int[] acc = new int[N + 1];
        for (int i = 1; i <= N; i++)
            acc[i] = acc[i - 1] ^ arr[i];

        // 2. Q번의 [s:e] 질문에 대해 누적 XOR 배열을 사용해 구간 XOR을 구한다.
        int ans = 0;
        while (Q-- > 0) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            ans ^= acc[e] ^ acc[s - 1];
        }
        System.out.println(ans);
    }
}
