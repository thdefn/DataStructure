package 정렬.풀이;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Comparator
 * 오름차순 첫번째 인자가 두번째 인자보다 작다면 음수, 같다면 0, 크다면 양수
 * 음수면 첫번째 인자 -> 두번째 인자
 */
public class 단어정렬 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.next();
        }

        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()){
                    return o1.compareTo(o2);
                }
                return o1.length() - o2.length();
            }
        });

        System.out.println(arr[0]);
        for (int i = 1; i < N; i++) {
            if(arr[i].equals(arr[i-1])) continue;
            System.out.println(arr[i]);
        }
    }
}
