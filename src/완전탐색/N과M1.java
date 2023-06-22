package 완전탐색;

import java.util.Scanner;

public class N과M1 {

    static int N, M;
    static int[] selected;
    static StringBuilder stringBuilder;

    // k번째 원소부터 M번째 원소를 조건에 맞는 모든 방법을 찾아줘
    static void recFunc(int k) {
        if (k == M + 1) {
            for (int i = 1; i <= M; i++) {
                stringBuilder.append(selected[i] + " ");
            }
            stringBuilder.append("\n");
        } else {
            for (int cand = 1; cand <= N ; cand++) {
                boolean isUsed = false;

                for (int i = 1; i < k; i++) {
                    if(cand == selected[i]) isUsed = true;
                }

                if(!isUsed){
                    selected[k] = cand;
                    // k+1 ~ M 번을 모두 탐색하는 일을 해야 함
                    recFunc(k+1);
                    //selected[k] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        selected = new int[M + 1];
        stringBuilder = new StringBuilder();
        recFunc(1);
        System.out.println(stringBuilder.toString());
    }
}
