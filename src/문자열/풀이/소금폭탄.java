package 문자열.풀이;

import java.util.Scanner;

public class 소금폭탄 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] a = sc.next().split(":");
        String[] b = sc.next().split(":");

        int secondValue = Integer.parseInt(b[2]) + 60 - Integer.parseInt(a[2]);
        int second = secondValue % 60;
        int minuteValue = Integer.parseInt(b[1]) - 1 + secondValue / 60 + 60 - Integer.parseInt(a[1]);
        int minute = minuteValue % 60;
        int hourValue = Integer.parseInt(b[0]) - 1 + minuteValue / 60 + 24 - Integer.parseInt(a[0]);
        int hour = hourValue % 24;

        if (second + minute + hour == 0) hour += 24;

        System.out.printf("%02d:%02d:%02d", hour, minute, second);
    }
}
