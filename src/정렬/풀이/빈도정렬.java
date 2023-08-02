package 정렬.풀이;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 빈도정렬 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();

        Integer[][] nums = new Integer[N][2];
        int count = 0;
        for (int i = 0; i < N; i++) {
            int number = sc.nextInt();
            boolean isExist = false;
            for (int j = 0; j < count; j++) {
                if (nums[j][0] == number) {
                    isExist = true;
                    nums[j][1]++;
                    break;
                }
            }
            if (!isExist) {
                nums[count][0] = number;
                nums[count++][1] = 1;
            }
        }

        Arrays.sort(nums, 0, count, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o2[1] - o1[1];
            }
        });

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < nums[i][1]; j++)
                System.out.print(nums[i][0] + " ");
        }
    }
}
