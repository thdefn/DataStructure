package 시뮬레이션;

import java.util.Objects;
import java.util.Scanner;

/**
 * A
 * A?B
 * A?B??C
 * <p>
 * C??B?A
 * <p>
 * A
 * A?B
 * A?B????B
 * A?BC?A?B
 */
public class 행운의바퀴 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        String before = "";
        String answer = "";
        boolean isExist = true;
        for (int i = 0; i < K; i++) {
            int times = sc.nextInt();
            String alpha = sc.next();
            System.out.println(alpha);
            if (Objects.equals(alpha, before)) {
                if (times % N != 0) {
                    isExist = false;
                    break;
                }
            } else {
                if (times % N == 0) {
                    isExist = false;
                    break;
                } else {
                    for (int j = 0; j < times - 1; j++) {
                        answer = answer + "?";
                    }
                    answer = answer + alpha;
                }
            }
            before = alpha;
        }

        if (isExist) {
            char start = answer.charAt(answer.length()-1);
            System.out.print(start);
            for (int i = answer.length() - 2; i >= 0; i--) {
                if(answer.charAt(i) == start) break;
                System.out.print(answer.charAt(i));
            }
        } else {
            System.out.println("!");
        }
    }
}
