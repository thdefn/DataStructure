package 시뮬레이션.풀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 판화 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String trace = br.readLine();
        char[][] graph = new char[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], '.');
        }

        // 좌표
        int currentX = 0;
        int currentY = 0;
        for (int i = 0; i < trace.length(); i++) {
            char d = trace.charAt(i);
            if (d == 'U' && (currentY - 1) >= 0) {
                graph[currentY][currentX] = (graph[currentY][currentX] == '-' | graph[currentY][currentX] == '+') ? '+' : '|';
                currentY--;
                graph[currentY][currentX] = (graph[currentY][currentX] == '-' | graph[currentY][currentX] == '+') ? '+' : '|';
            } else if (d == 'D' && (currentY + 1) < N) {
                graph[currentY][currentX] = (graph[currentY][currentX] == '-' | graph[currentY][currentX] == '+') ? '+' : '|';
                currentY++;
                graph[currentY][currentX] = (graph[currentY][currentX] == '-' | graph[currentY][currentX] == '+') ? '+' : '|';
            } else if (d == 'L' && (currentX - 1) >= 0) {
                graph[currentY][currentX] = (graph[currentY][currentX] == '|' | graph[currentY][currentX] == '+') ? '+' : '-';
                currentX--;
                graph[currentY][currentX] = (graph[currentY][currentX] == '|' | graph[currentY][currentX] == '+') ? '+' : '-';
            } else if (d == 'R' && (currentX + 1) < N) {
                graph[currentY][currentX] = (graph[currentY][currentX] == '|' | graph[currentY][currentX] == '+') ? '+' : '-';
                currentX++;
                graph[currentY][currentX] = (graph[currentY][currentX] == '|' | graph[currentY][currentX] == '+') ? '+' : '-';
            }
        }

        for (char[] arr : graph) {
            for (char c : arr) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
