package 문자열.풀이;

import java.util.Scanner;

public class 문서검색 {
    public static int getWordTimes(String document, String word) {
        String str = document.replaceAll(word, "?");
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '?') count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String document = sc.nextLine();
        String word = sc.nextLine();
        int times = 0;
        for (int i = 0; (document.length() - i) >= word.length(); i++) {
            if (document.startsWith(word)) {
                times = getWordTimes(document.substring(i), word);
            }
        }
        System.out.println(times);
    }
}
