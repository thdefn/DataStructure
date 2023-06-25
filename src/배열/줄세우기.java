package 배열;

import java.util.Scanner;

/**
 * 삽입 정렬 O(T*P^2)
 */
public class 줄세우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int P = sc.nextInt();
        // for (int i = P; i > 0; i--)
        // O(P * N2) = O(P) * {O(N) + O(N)*O(N) + O(N)*O(N)}
        while (P-- > 0) {
            int T = sc.nextInt();
            int[] h = new int[20];
            // O(N)
            for (int i = 0; i < 20; i++) {
                h[i] = sc.nextInt();
            }
            int cnt = 0;
            int[] sorted = new int[20];

            // O(N)
            for (int i = 0; i < 20; i++) {
                // 1. 줄 서 있는 학생 중에 자신보다 큰 학생을 찾는다.
                // 만약 찾지 못했다면, 줄의 가장 뒤에 선다
                boolean find = false;
                // O(N)
                for (int j = 0; j < i; j++) {
                    if (sorted[j] > h[i]) {
                        find = true;
                        // 2. 찾았다면, 그 학생의 앞에 선다.
                        // 3. 그 학생과 그 뒤의 학생이 모두 한 칸씩 물러난다.
                        // O(N)
                        for (int k = i - 1; k >= j; k--) {
                            sorted[k + 1] = sorted[k];
                            cnt++;
                        }
                        sorted[j] = h[i];
                        break;
                    }
                }
                // j 값을 기록해서 여기에서 이동 처리할 수 있다
                if (!find) sorted[i] = h[i];
            }

            System.out.println(T + " " + cnt);
        }
    }
}
