package leet150.array;

/**
 * i : 0 1 2 3
 * m : 1 2 3 4
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        int[] num1 = {4, 0, 0, 0, 0, 0};
        int[] num2 = {1, 2, 3, 5, 6};
        int m = 1;
        int n = 5;
        merge(num1, m, num2, n);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int cnt = 0;
        int i = 0;
        while (cnt < n) {
            if (nums1[i] >= nums2[cnt]) {
                // i 에 집어넣는다
                for (int j = m; j >= i + 1; j--)
                    nums1[j] = nums1[j - 1];
                nums1[i] = nums2[cnt++];
                m++;
            } else if (i == m) {
                nums1[i] = nums2[cnt++];
                m++;
            }
            i++;
        }
    }
}
