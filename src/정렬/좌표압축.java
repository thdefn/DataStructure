package 정렬;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 2차원 배열에 대한 정렬은 Comparator 사용
 * Map - 특정값에 대한 동일한 계산값 저장
 * Set - TreeSet 중복 제거
 */
public class 좌표압축 {
    public static void main2(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        // 1. 입력된 좌표를 작은 순으로 정렬한다.
        int[] xs = new int[N];
        Set<Integer> set = new TreeSet<>();
        // NLog(N)
        for (int i = 0; i < N; i++) {
            xs[i] = sc.nextInt();
            set.add(xs[i]);
        }

        // 2. 정렬된 좌표를 중복을 제거하며 압축된 인덱스를 기록한다.
        // N
        Map<Integer, Integer> sortedIndex = new HashMap<>();
        int idx = 0;
        for (int x : set)
            sortedIndex.put(x, idx++);

        // 3. 입력된 좌표에 알맞은 압축 인덱스를 출력한다.
        // N
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++)
            bw.write(sortedIndex.get(xs[i]) + " ");
        bw.write("\n");
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 1. 입력된 좌표를 작은 순으로 정렬한다.
        int[][] xs = new int[N][2]; // 0: x, 1: inputIndex
        for (int i = 0; i < N; i++) {
            xs[i][0] = sc.nextInt();
            xs[i][1] = i;
        }

        Arrays.sort(xs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 2. 정렬된 좌표를 중복을 제거하며 압축된 인덱스를 기록한다.
        int[] ans = new int[N];
        int idx = 0;
        ans[xs[0][1]] = idx;
        for (int i = 1; i < N; i++) {
            if (xs[i][0] != xs[i - 1][0]) idx++;
            ans[xs[i][1]] = idx;
        }

        // 3. 입력된 좌표에 알맞은 압축 인덱스를 출력한다
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++)
            bw.write(ans[i] + " ");
        bw.write("\n");
        bw.flush();
    }
}
