package 배열;

import java.io.*;

/**
 * BufferedReader
 * BufferedWriter
 */
public class 빠른A더하기B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] nums = br.readLine().split(" ");
            int a = Integer.parseInt(nums[0]);
            int b = Integer.parseInt(nums[1]);
            bw.write(a + b + "\n");
        }
        bw.flush();
    }
}
