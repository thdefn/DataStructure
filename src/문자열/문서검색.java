package 문자열;

import java.util.Scanner;

public class 문서검색 {

    public static int getWordTimes(String document, String word) {
        String replaced = document.replace(word, "");
        int length = document.length() - replaced.length();
        return length / word.length();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String document = sc.nextLine();
        String word = sc.nextLine();

        int count = 0;
        int startIndex = 0;

        while (true) {
            int findIndex = document.indexOf(word, startIndex);
            if (findIndex < 0) break;
            count++;
            startIndex = findIndex + word.length();
        }
        System.out.println(count);
    }
}
