package 배열;

import java.io.*;

public class 수정렬하기3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] cnt = new int[10001];

        //O(N)
        for (int i = 0; i < N; i++) {
            cnt[Integer.parseInt(br.readLine())]++;
        }

        //O(N) = O(10000 + N)
        // N이 10000보다 작을 때 O(10000), N이 10000 이상일 때 O(N)
        for (int i = 1; i <= 10000; i++) {
            while (cnt[i]-- > 0) {
                bw.write(i + "\n");
            }
        }
        bw.flush();
    }
}
