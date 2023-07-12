package 시뮬레이션;

import java.util.Scanner;

/**
 * 런타임 에러 (NoSuchElement)
 * 문자열의 최소 길이가 없음 문자열이 만약 입력되지 않는다면?
 *
 * 5
 *
 * sc.next(); -> 공백을 제외하고 다음에 오는 문자열을 받음
 * 아무것도 입력되지 않은 경우 공백을 제외하면 남은 게 없어서 NoSuchElement 익셉션 발생
 */
public class 판화 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String command = sc.hasNext() ? sc.next() : "";
        int curR = 0;
        int curC = 0;
        boolean[][] passVertical = new boolean[N][N];
        boolean[][] passHorizontal = new boolean[N][N];
        for (int i = 0; i < command.length(); i++) {
            char cmd = command.charAt(i);
            if (cmd == 'D') {
                if (curR == N - 1) continue;
                passVertical[curR][curC] = passVertical[curR + 1][curC] = true;
                curR++;
            } else if (cmd == 'U') {
                if (curR == 0) continue;
                passVertical[curR][curC] = passVertical[curR - 1][curC] = true;
                curR--;
            } else if (cmd == 'L') {
                if (curC == 0) continue;
                passHorizontal[curR][curC] = passHorizontal[curR][curC - 1] = true;
                curC--;
            } else {
                if (curC == N - 1) continue;
                passHorizontal[curR][curC] = passHorizontal[curR][curC + 1] = true;
                curC++;
            }
        }

        for (int i = 0; i < N; i++) {
            String ans = "";
            for (int j = 0; j < N; j++) {
                if (passVertical[i][j] && passHorizontal[i][j]) ans += "+";
                else if (passVertical[i][j]) ans += "|";
                else if (passHorizontal[i][j]) ans += "-";
                else ans += ".";
            }
            System.out.println(ans);
        }
    }
}
