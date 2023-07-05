package 완전탐색;

import java.util.Scanner;

public class 회문인수 {

    // O(K) = O(LogB(X)), B^K = X, K = LogB(X)
    public static int[] convertBase(int x, int B) {
        int len = 0;
        int copyX = x;
        while (copyX > 0) {
            copyX /= B;
            len++;
        }

        int[] digit = new int[len];
        len = 0;
        while (x > 0) {
            digit[len++] = x % B;
            x /= B;
        }
        return digit;
    }

    // LogB(X)
    public static boolean isPalindrome(int[] digit) {
        for (int i = 0; i < digit.length / 2; i++) {
            if (digit[i] != digit[digit.length - i - 1])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int x = sc.nextInt();
            boolean ans = false;
            // O(64 * LogB(X)) = O(64 * LogB(X) + LogB(X))
            for (int i = 2; i <= 64; i++) {
                int[] digit = convertBase(x, i);
                if (isPalindrome(digit)) {
                    ans = true;
                    break;
                }
            }
            System.out.println(ans ? "1" : "0");
        }
    }
}
