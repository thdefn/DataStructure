package 완전탐색.풀이;

import java.util.Scanner;

public class 유레카이론 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] nums = new int[T];
        for (int i = 0; i < T; i++) {
            nums[i] = sc.nextInt();
        }

        int[] triNums = new int[1001];
        int num = 0;
        for (int i = 1; num < 1000; i++) {
            num = ((i * (i+1))/2);
            triNums[i] = num;
        }

        boolean[] ans = new boolean[T];
        for (int i = 0; i < T; i++) {
            for (int j = 1; triNums[j] < nums[i]; j++) {
                boolean end = false;
                for (int k = j; triNums[k] < nums[i]; k++) {
                    for (int l = k; triNums[l] < nums[i] ; l++) {
                        if(triNums[l] == 0) {
                            end = true;
                            break;
                        }
                        if (triNums[j] + triNums[k] + triNums[l] == nums[i]){
                            ans[i] = true;
                            break;
                        }
                    }
                    if(end) break;
                }
                if(end) break;
            }
            System.out.println(ans[i] ? 1 : 0);
        }

    }
}
