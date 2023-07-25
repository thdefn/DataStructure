package 정렬;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * o1.compareTo(o2); : o1이 사전순으로 앞이라면 음수를 반환함
 * compare(Object o1, Object o2) : o1이 앞이라면 음수를 반환함, o2가 앞이라면 양수를 반환함
 */
public class 단어정렬 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
        }

        // 1. 길이가 짧은 것부터
        // 2. 길이가 같으면 사전 순으로
        // O(L * NLogN)
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length())
                    return o1.compareTo(o2);
                return o1.length() - o2.length();
            }
        });

        // 중복된 단어는 하나만 남기고 제거해야 한다.
        // O(N)
        System.out.println(words[0]);
        for (int i = 1; i < N; i++) {
            if (words[i].equals(words[i - 1])) continue;
            System.out.println(words[i]);
        }
    }
}
