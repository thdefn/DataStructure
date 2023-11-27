package 기출.카카오;

import java.util.*;

public class 주사위 {
    public static void main(String[] args) {

    }

    public int[] solution(int[][] dice) {

        Map<String, int[]> possibleSumMap = new HashMap<>();
        for (int i = 0; i < dice.length; i++) {
            for (int j = i + 1; j < dice.length; j++) {
                int[] possibleSumCount = new int[36];
                for (int k = 0; k < 6; k++) {
                    for (int l = 0; l < 6; l++) {
                        possibleSumCount[l] = dice[i][k] + dice[j][l];
                    }
                }
                Arrays.sort(possibleSumCount);
                possibleSumMap.put(String.valueOf(i + j), possibleSumCount);
            }
        }

        int maxWinCount = 0;
        for (int i = 0; i < dice.length; i++) {
            for (int j = i + 1; j < dice.length; j++) {
                String keyA = String.valueOf(i + j);
                int[] possibleSumCountA = possibleSumMap.get(String.valueOf(i + j));
                for (String keyB : possibleSumMap.keySet()) {
                    int winCount = 0;
                    if (keyA.equals(keyB)) continue;
                    int[] possibleSumCountB = possibleSumMap.get(keyB);
                    for (int k = 0; k < 36; k++) {
                        // x 이상의 값이 언제 나타나나
                        int index = findLowerBoundIndex(possibleSumCountB, possibleSumCountA[k]);
                        winCount += index;
                    }

                }
            }
        }


        int[] answer = {};
        return answer;
    }

    static int findLowerBoundIndex(int[] arr, int x) {
        int lowerBoundIndex = arr.length;
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
}
