package 시뮬레이션;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 환형
 * 배열의 끝과 끝이 연결된 것 처럼 활용
 * 글자의 위치를 고정하고, 커서의 위치를 변경
 *
 * 선형 구조의 인덱스를 이용한 환형 구조 표현 -> 모듈로 연산
 */
public class 행운의바퀴 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        char[] wheel = new char[N];
        Arrays.fill(wheel, '?');
        int curIndex = 0;
        while (K-- > 0) {
            int step = sc.nextInt();
            char nextAlphabet = sc.next().charAt(0);
            int nextIndex = ((curIndex - step) % N + N) % N;
            if (wheel[nextIndex] == '?') wheel[nextIndex] = nextAlphabet;
            else if (wheel[nextIndex] != nextAlphabet) {
                System.out.println("!");
                return;
            }
            curIndex = nextIndex;
        }

        boolean[] used = new boolean[26];
        for (int i = 0; i < N; i++) {
            if (wheel[i] == '?') continue;
            if (used[wheel[i] - 'A']) {
                System.out.println("!");
                return;
            }
            used[wheel[i] - 'A'] = true;
        }

        // 마지막에 기록된 인덱스부터 떨어진 순서로 출력, 인덱스 증가하는 과정에서 N이 넘는 부분은 모듈로 처리
        for (int i = 0; i < N; i++)
            System.out.print(wheel[(curIndex + i) % N]);
        System.out.println();
    }
}
