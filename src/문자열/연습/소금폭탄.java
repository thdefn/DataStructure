package 문자열.연습;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class 소금폭탄 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] a = Arrays.stream(sc.next().split(":"))
                .flatMapToInt(str -> IntStream.of(Integer.parseInt(str)))
                .toArray();
        int[] b = Arrays.stream(sc.next().split(":"))
                .flatMapToInt(str -> IntStream.of(Integer.parseInt(str)))
                .toArray();

        int secondValue = b[2] + 60 - a[2];
        int second = secondValue % 60;
        int minuteValue = b[1] - 1 + secondValue / 60 + 60 - a[1];
        int minute = minuteValue % 60;
        int hourValue = b[0] - 1 + minuteValue / 60 + 24 - a[0];
        int hour = hourValue % 24;

        if (second + minute + hour == 0) hour += 24;

        System.out.printf("%02d:%02d:%02d", hour, minute, second);
    }
}
