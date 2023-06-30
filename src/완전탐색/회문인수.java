package 완전탐색;

import java.util.Scanner;

public class 회문인수 {
    public static boolean isPalindrome(String str) {
        for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) != str.charAt(str.length() - 1 - j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] nums = new int[T];
        for (int i = 0; i < T; i++) {
            nums[i] = sc.nextInt();
        }

        for (int i = 0; i < T; i++) {
            boolean hasPalindrome = false;
            for (int B = 2; B <= 64; B++) {
                String str = "";
                if (B != 10) {
                    int number = nums[i];
                    while (number >= B) {
                        str = ((char) (number % B + '!')) + str;
                        number = number / B;
                    }

                    if ((number % B) > 0)
                        str = ((char) (number % B + '!')) + str;
                } else {
                    str = String.valueOf(nums[i]);
                }

                if (isPalindrome(str)) {
                    hasPalindrome = true;
                    break;
                }
            }
            System.out.println(hasPalindrome ? "1" : "0");
        }
    }
}
