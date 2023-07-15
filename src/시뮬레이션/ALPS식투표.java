package 시뮬레이션;

import java.util.Arrays;
import java.util.Scanner;

public class ALPS식투표 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int N = sc.nextInt();
        char[] staffs = new char[N];
        int[][] scores = new int[N][14];

        int maxScore = 0;
        int maxIndex = 0;
        for (int i = 0; i < N; i++) {
            staffs[i] = sc.next().charAt(0);
            int numberOfVotes = sc.nextInt();
            if (numberOfVotes * 20 < X) continue;
            if (numberOfVotes > maxScore) {
                maxScore = numberOfVotes;
                maxIndex = i;
            }
            for (int j = 0; j < 14; j++) {
                scores[i][j] = numberOfVotes / (j + 1);
            }
        }

        char[] alpsStaff = new char[14];
        Arrays.fill(alpsStaff, staffs[maxIndex]);
        int[] alpsScore = scores[maxIndex].clone();
        for (int i = 0; i < N; i++) {
            if (i == maxIndex) continue;
            // 스태프의 점수를 넣을 위치를 찾는다
            for (int j = 0; j < 14; j++) {
                // 알프스 스코어 최소값보다는 스태프의 점수가 커야한다
                if (alpsScore[13] > scores[i][j]) break;
                for (int k = 12; k >= 0; k--) {
                    // 스태프의 점수가 알프스 스코어 점수보다 크면 해당 지점에 삽입한다
                    if (alpsScore[k] > scores[i][j]) {
                        for (int l = 13; l >= k + 2; l--) {
                            alpsScore[l] = alpsScore[l - 1];
                            alpsStaff[l] = alpsStaff[l - 1];
                        }
                        alpsScore[k + 1] = scores[i][j];
                        alpsStaff[k + 1] = staffs[i];
                        break;
                    }
                }
            }
        }

        int[] answer = new int[26];
        for (int i = 0; i < 14; i++) {
            answer[alpsStaff[i] - 'A']++;
        }

        for (int i = 0; i < 26; i++) {
            if (answer[i] == 0) continue;
            System.out.println((char) (i + 'A') + " " + answer[i]);
        }

    }
}
