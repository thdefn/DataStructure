package leet150.twopointers;

public class ValidPalindrome {
    public static void main(String[] args) {
        String s = "0P";
        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char a = Character.toUpperCase(s.charAt(i));
            char b = Character.toUpperCase(s.charAt(j));
            if (!isValidCharacter(a)) {
                i++;
                continue;
            }
            if (!isValidCharacter(b)) {
                j--;
                continue;
            }
            if (a == b) {
                i++;
                j--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return true;
        } else return ch >= '0' && ch <= '9';
    }
}
