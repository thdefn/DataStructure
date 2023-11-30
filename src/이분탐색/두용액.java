package 이분탐색;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * O(N * Log(N))
 *
 * 99와 가장 가까운 값을 찾는 데 binary search 사용
 */
public class 두용액 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        // 1. 첫 번째 용액에 대해 최적쌍이 되는 두 번째 용액을 찾는다.
        Arrays.sort(arr);
        int ansAbs = 2000000000;
        int ans1 = 0;
        int ans2 = 0;
        // O(N * Log(N))
        for (int i = 0; i < N - 1; i++) {
            int pairValue = findOptimalPairValue(arr, i + 1, N - 1, arr[i]);
            int abs = Math.abs(arr[i] + pairValue);
            if(abs < ansAbs){
                ansAbs = abs;
                ans1 = arr[i];
                ans2 = pairValue;
            }
        }

        // 2. 두 용액의 합이 0에 가장 가까운 쌍을 출력한다.
        System.out.println(ans1 + " " + ans2);
    }

    // O(Log(N))
    private static int findOptimalPairValue(int[] arr, int fromIndex, int toIndex, int value) {
        int optimalPairAbs = 2000000000;
        int optimalPairValue = 0;
        int l = fromIndex + 1;
        int r = toIndex;
        while (l <= r) {
            int m = (l + r) / 2;
            int sum = value + arr[m];
            int sumAbs = Math.abs(sum);
            if (sumAbs < optimalPairAbs) {
                optimalPairValue = arr[m];
                optimalPairAbs = sumAbs;
            }

            if (sum < 0) l = m + 1;
            else if (sum > 0) r = m - 1;
            else return arr[m];
        }
        return optimalPairValue;
    }


    /**
     * TreeSet 의 floor 와 ceiling 을 사용하면 찾고자 하는 값과 가장 가까운 값을 찾을 수 있다.
     * floor(x) : x 이하이고 x와 가장 가까운 숫자 리턴
     * ceiling(x) : x 이상이고 x와 가장 가까운 숫자 리턴
     */
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int ansAbs = 2000000001;
        int ans1 = 0;
        int ans2 = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            Integer[] pairValues = {set.floor(-x), set.ceiling(-x)};
            for (Integer pairValue : pairValues) {
                if (pairValue == null) continue;
                int sumAbs = Math.abs(x + pairValue);
                if (ansAbs > sumAbs) {
                    ansAbs = sumAbs;
                    ans1 = x;
                    ans2 = pairValue;
                }
            }
            // -99를 조사할 때 -99가 들어가 있으면 안되므로 특정 값 조사가 끝나고 나서 값 추가
            set.add(x);
        }
    }
}
