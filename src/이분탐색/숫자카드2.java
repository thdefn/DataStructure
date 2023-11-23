package 이분탐색;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 100000개 되는 출력은 BufferedWriter로 바꿔주자
 */
public class 숫자카드2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] cards = new int[N];
        for (int i = 0; i < N; i++) {
            cards[i] = sc.nextInt();
        }

        Arrays.sort(cards);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int M = sc.nextInt();
        while (M-- > 0){
            int x = sc.nextInt();
            // 1. x 이상의 값이 처음으로 나타나는 위치를 구한다.
            int lowerBoundIndex = findLowerBoundIndex(cards, x);
            // 2. x 초과의 값이 처음을 나타나는 위치를 구한다.
            int upperBoundIndex = findUpperBoundIndex(cards, x);
            // 3. 두 값을 이용해 x의 개수를 센다
            bw.write(upperBoundIndex - lowerBoundIndex + " ");
        }
        bw.write("\n");
        bw.flush();
    }


    static int findLowerBoundIndex(int[] arr, int x) {
        // X 이상의 값이 처음으로 나타나는 위치
        // 변수를 따로 두고 최적값을 갱신
        int lowerBoundIndex = arr.length; // X 이상의 값이 없는 경우 조사 범위 하나 밖을 리턴
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] < x) l = m + 1;
            else {
                // arr[m]이 x보다 크거나 같을때
                r = m - 1; // 탐색 범위를 줄이고
                lowerBoundIndex = m; // 인덱스 갱신
            }
        }
        return lowerBoundIndex;
    }

    static int findUpperBoundIndex(int[] arr, int x) {
        // X 초과의 값이 처음으로 나타나는 위치
        int upperBoundIndex = arr.length;
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] <= x) l = m + 1;
            else {
                // arr[m]이 x보다 클때
                r = m - 1;
                upperBoundIndex = m;
            }
        }
        return upperBoundIndex;
    }
}
