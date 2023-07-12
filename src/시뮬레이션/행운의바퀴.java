package 시뮬레이션;

import java.util.Arrays;
import java.util.Scanner;

/**
 * HONITAVR
 * H???????
 * HO??????
 * HON?????
 * HON????R
 * HON??A?R
 * HON?TA?R
 * HONITA?R
 * HONITAVR
 */
public class 행운의바퀴 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] times = new int[K];
        char[] alpha = new char[K];
        for (int i = 0; i < K; i++) {
            times[i] = sc.nextInt();
            alpha[i] = sc.next().charAt(0);
        }

        char[] wheel = new char[N];
        Arrays.fill(wheel, '?');
        wheel[0] = alpha[K - 1];
        int beforeIndex = 0;
        boolean isExist = true;
        for (int i = K - 2; i >= 0; i--) {
            int currentIdx = (beforeIndex + times[i + 1] % N) % N;

            // 바로 전 알파벳이 현재 알파벳이면 인덱스가 같아야 한다
            if (wheel[beforeIndex] == alpha[i] && currentIdx != beforeIndex) {
                isExist = false;
                break;
            }
            // 넣으려는 인덱스가 '?'가 아니라면 현재 알파벳과 넣으려는 인덱스의 알파벳이 같아야 한다
            if (wheel[currentIdx] != '?' && wheel[currentIdx] != alpha[i]) {
                isExist = false;
                break;
            }

            // 현재 알파벳이 다른 인덱스에 있으면 안된다
            boolean isDuplicated = false;
            for (int j = 0; j < N; j++) {
                if (wheel[j] == alpha[i] && j != currentIdx) {
                    isDuplicated = true;
                    break;
                }
            }

            if (isDuplicated) {
                isExist = false;
                break;
            }

            wheel[currentIdx] = alpha[i];
            beforeIndex = currentIdx;
        }

        if (isExist) {
            for (int i = 0; i < N; i++) {
                System.out.print(wheel[i]);
            }
        } else {
            System.out.print("!");
        }
    }

}
