package 구간합;

import java.util.Scanner;

/**
 * 구간에 일괄적으로 누적 연산을 적용할 수 있는 경우
 */
public class 태상이의훈련소생활 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] origin = new int[N + 1];
        for (int i = 1; i <= N; i++)
            origin[i] = sc.nextInt();

        int[] delta = new int[N + 2];
        while (M-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int k = sc.nextInt();
            // 1. 각 지시에 따른 변화량 delta 를 기록한다.
            // delta[i]: [i:N] 까지 적용되는 변화량
            delta[a] += k;
            // b + 1 부터는 +k를 상쇄하기 위함
            delta[b + 1] -= k;
        }

        // 2. 각 칸부터의 변화량을 각 칸에 적용한다.
        int[] accDelta = new int[N + 1];
        for (int i = 1; i <= N; i++)
            accDelta[i] = accDelta[i - 1] + delta[i];

        // 3. 변화량이 적용된 각 칸의 높이를 출려갛ㄴ다
        for (int i = 1; i <= N ; i++)
            System.out.print(origin[i] + accDelta[i] + " ");
        System.out.println();
    }
}
