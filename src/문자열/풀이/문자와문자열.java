package 문자열.풀이;

import java.util.Scanner;

public class 문자와문자열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int num = Integer.parseInt(sc.next());
        System.out.printf("%c",str.charAt(num-1));
    }
}
