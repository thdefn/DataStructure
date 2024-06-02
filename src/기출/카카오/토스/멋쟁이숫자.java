package 기출.카카오.토스;

import java.util.Arrays;

public class 멋쟁이숫자 {
    public static void main(String[] args) {
        String s = "111999333";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        if(s.length() < 3) return -1;
        int answer = -1;
        int number = -1;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i) - '0';
            if(number != cur){
                number = cur;
                count = 0;
            }
            count++;
            if(count == 3){
                int newNumber = cur + cur * 10 + cur * 100;
                answer = Math.max(answer, newNumber);
            }
        }
        return answer;
    }
}
