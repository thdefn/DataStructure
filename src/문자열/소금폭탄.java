package 문자열;

import java.util.Scanner;

public class 소금폭탄 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] current = sc.next().split(":");
        String[] drop = sc.next().split(":");

        int currentHour = Integer.parseInt(current[0]);
        int currentMinute = Integer.parseInt(current[1]);
        int currentSecond = Integer.parseInt(current[2]);
        int currentSecondAmount = currentHour * 3600 + currentMinute * 60 + currentSecond;

        int dropHour = Integer.parseInt(drop[0]);
        int dropMinute = Integer.parseInt(drop[1]);
        int dropSecond = Integer.parseInt(drop[2]);
        int dropSecondAmount = dropHour * 3600 + dropMinute * 60 + dropSecond;

        int needSecondAmount = dropSecondAmount - currentSecondAmount;
        if (needSecondAmount < 0) needSecondAmount += 24 * 3600;

        int needHour = needSecondAmount / 3600;
        int needMinute = (needSecondAmount % 3600) / 60;
        int needSecond = needSecondAmount % 60;

        System.out.printf("%02d:%02d:%02d", needHour, needMinute, needSecond);
    }
}
