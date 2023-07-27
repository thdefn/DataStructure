package 정렬;

import java.io.*;
import java.util.Arrays;

public class 좌표압축 {
    static class Coordinate implements Comparable<Coordinate> {
        int index;
        int x;

        Coordinate(int index, int x) {
            this.index = index;
            this.x = x;
        }

        @Override
        public int compareTo(Coordinate o) {
            return x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Coordinate[] coordinates = new Coordinate[N];
        String[] nums = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            coordinates[i] = new Coordinate(i, Integer.parseInt(nums[i]));
        }
        Arrays.sort(coordinates);

        int[] index = new int[N];
        int beforeValue = coordinates[0].x;
        coordinates[0].x = 0;
        index[coordinates[0].index] = coordinates[0].x;
        for (int i = 1; i < N; i++) {
            if (coordinates[i].x == beforeValue) {
                beforeValue = coordinates[i].x;
                coordinates[i].x = coordinates[i - 1].x;
            } else {
                beforeValue = coordinates[i].x;
                coordinates[i].x = coordinates[i - 1].x + 1;
            }
            index[coordinates[i].index] = coordinates[i].x;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++)
            bw.write(index[i] + " ");
        bw.flush();

    }
}
