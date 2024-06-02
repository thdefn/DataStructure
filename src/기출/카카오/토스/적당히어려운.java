package 기출.카카오.토스;

import java.util.Arrays;

public class 적당히어려운 {
    public static void main(String[] args) {
        int[] levels = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(solution(levels));
    }

    public static int solution(int[] levels) {
        if(levels.length <= 3) return -1;
        int targetIdx = (int) Math.ceil(levels.length * 0.75);
        Arrays.sort(levels);
        return levels[targetIdx];
    }
}
