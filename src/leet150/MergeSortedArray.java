package leet150;

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
        for (int i = 0; i < m + n; i++) {
            if (cnt == n) break;
            if (nums1[i] >= nums2[cnt]) {
                // i 에 집어넣는다
                for (int j = nums1.length - 1; j >= i + 1; j--)
                    nums1[j] = nums1[j - 1];
                nums1[i] = nums2[cnt++];
            }
        }

        for (int i = cnt; i < n; i++)
            nums1[m + i] = nums2[i];
    }
}
