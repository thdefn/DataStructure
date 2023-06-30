package 완전탐색;


import java.util.Scanner;

public class 진법변환2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int B = sc.nextInt();

        String answer = "";
        String digit;
        int remain;
        while (N >= B) {
            remain = N % B;
            digit = remain < 10 ?
                    String.valueOf(remain) : String.valueOf((char) (remain - 10 + 'A'));
            answer = digit + answer;
            N = N / B;
        }
        remain = N % B;
        digit = remain < 10 && remain > 0 ?
                String.valueOf(remain) : String.valueOf((char) (remain - 10 + 'A'));

        answer = digit + answer;
        System.out.println(answer);
    }
}
