package 기출.카카오.토스;

import java.util.HashSet;
import java.util.Set;

public class 영수증 {
    public static void main(String[] args) {
        String s = "300000";
        System.out.println(solution(s));
    }

    public static boolean solution(String amountText) {
        boolean answer = true;
        boolean colonExisted = false;

        if (amountText.charAt(0) == ',') return false;
        else if (amountText.charAt(0) == '0' && amountText.length() > 1) {
            return false;
        }

        if (amountText.length() >= 5) {
            if (amountText.charAt(amountText.length() - 4) == ',')
                colonExisted = true;
        }

        Set<Character> allowed = new HashSet<>();
        allowed.add(',');
        for (int i = 0; i <= 9; i++) {
            allowed.add((char) ('0' + i));
        }

        for (int i = 1; i <= amountText.length(); i++) {
            char c = amountText.charAt(amountText.length() - i);
            if (!allowed.contains(c))
                return false;
            if (c == ',') {
                if (!colonExisted)
                    return false;
                if (i % 4 != 0)
                    return false;
            }
        }
        return answer;
    }
}
