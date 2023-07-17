package 시뮬레이션;

import java.util.Scanner;

public class ALPS식투표 {
    static class Score {
        Score(int staffIndex, double scr) {
            this.staffIndex = staffIndex;
            this.scr = scr;
        }

        int staffIndex;
        double scr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int N = sc.nextInt();

        // 1. 전체 득표수 5% 미만의 스태프를 후보에서 제외한다.
        double validCut = X * 0.05;
        boolean[] validCandidate = new boolean[26];
        int[] staffVote = new int[26];
        int candidateCount = 0;
        for (int i = 0; i < N; i++) {
            String name = sc.next();
            int vote = sc.nextInt();
            if (vote >= validCut) {
                int index = name.charAt(0) - 'A';
                validCandidate[index] = true;
                staffVote[index] = vote;
                candidateCount++;
            }
        }
        // 2. 남은 스태프마다 받은 득표수를 1~14로 나눈 점수 집합을 구한다.
        // 각각의 점수를 누가 받았는지 알아야 칩을 지급할 수 있다 -> Score 클래스 활용
        Score[] scores = new Score[candidateCount * 14];
        int scoreIndex = 0;
        for (int i = 0; i < 26; i++) {
            if (validCandidate[i]) {
                for (int j = 1; j <= 14; j++) {
                    scores[scoreIndex++] = new Score(i, (double) staffVote[i] / j);
                }
            }
        }

        // 3. 전체 점수 집합에서 점수가 큰 1~14번째 스태프에게 칩을 한 개씩 지급한다.
        sortScoresDescendingOrder(scores);
        int[] ans = new int[26];
        for (int i = 0; i < 14; i++) {
            ans[scores[i].staffIndex]++;
        }

        // 4. 스태프 이름에 대해 사전순으로 후보 스태프와 받은 칩의 수를 출력한다.
        for (int i = 0; i < 26; i++) {
            if (validCandidate[i])
                System.out.println((char) (i + 'A') + " " + ans[i]);
        }
    }

    // O(N^2) = O((validateCount * 14)^2)
    private static void sortScoresDescendingOrder(Score[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 나보다 작은 애를 찾아서 그 앞에 선다.
            for (int j = 0; j < i; j++) {
                // 나보다 작은 애가 내 앞에 있다면
                if (arr[i].scr > arr[j].scr) {
                    // j부터 i 이전 값들이 한 칸씩 뒤로 가고, j에 현재값 넣는다
                    Score cur = arr[i];
                    for (int k = i; k > j; k--) {
                        arr[k] = arr[k - 1];
                    }
                    arr[j] = cur;
                    break;
                }
            }
        }
    }
}
