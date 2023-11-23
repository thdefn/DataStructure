package 이분탐색.풀이;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 숫자카드2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int number = sc.nextInt();
            countMap.put(number, countMap.getOrDefault(number, 0) + 1);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int M = sc.nextInt();
        while (M-- > 0){
            bw.write(countMap.getOrDefault(sc.nextInt(), 0) + " ");
        }
        bw.write("\n");
        bw.flush();
    }

}
