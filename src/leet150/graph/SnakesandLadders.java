package leet150.graph;

import java.util.*;

public class SnakesandLadders {

    public static int snakesAndLadders(int[][] board) {
        // 인덱스 처리
        int n = board.length;
        int[][] indexes = new int[n * n + 1][2]; // 인덱스 : [x, y]
        int index = 1;
        boolean isForward = true;

        for (int i = n - 1; i >= 0; i--) {
            if (isForward) {
                for (int j = 0; j < n; j++) {
                    indexes[index][0] = i;
                    indexes[index][1] = j;
                    index++;
                }
                isForward = false;
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    indexes[index][0] = i;
                    indexes[index][1] = j;
                    index++;
                }
                isForward = true;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> distances = new HashMap<>();


        int cur = 1;
        q.offer(cur);
        distances.put(cur, 0);
        while (!q.isEmpty()) {
            cur = q.remove();

            int distance = distances.get(cur);
            if (cur == n * n) return distance;
            for (int i = 1; i <= 6; i++) {
                int next = cur + i;
                if (next > n * n) break;
                int r = indexes[next][0];
                int c = indexes[next][1];
                if (board[r][c] != -1) {
                    next = board[r][c];
                }

                if (!distances.containsKey(next)) {
                    distances.put(next, distance + 1);
                    q.offer(next);
                }
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        int[][] b =              {
                {-1,42,12,-1,1,-1,-1}, //43 44 45 46 47 48 49
                {-1,-1,5,-1,-1,46,44},   //42 41 40 39 38 37 36
                {18,22,6,39,-1,-1,-1}, //29 30 31 32 33 34 35
                {-1,-1,40,-1,-1,-1,37}, //28 27 26 25 24 23 22
                {49,38,24,-1,14,29,-1},   //15 16 17 18 19 20 21
                {-1,-1,6,-1,-1,-1,20}, //14 13 12 11 10 9 8
                {-1,-1,12,10,-1,5,26} //1 2 3 4 5 6 7
        };

//
//                {
//                {-1,-1,2,-1}, //16 15 14 13
//                {14,2,12,3},   //9 10 11 12
//                {4,9,1,11}, //8 7 6 5
//                {-1,2,1,16}, //1 2 3 4
//        };
//                {
//                {-1, -1, 19, 10, -1}, //21 22 23 24 25
//                {2, -1, -1, 6, -1},   //20 19 18 17 16
//                {-1, 17, -1, 19, -1}, //11 12 13 14 15
//                {25, -1, 20, -1, -1}, //10 9 8 7 6
//                {-1, -1, -1, -1, 15}, // 1 2 3 4 5
//        };
        System.out.println(snakesAndLadders(b));
    }
}
