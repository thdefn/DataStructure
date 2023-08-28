package leet150.binarysearch;

public class Searcha2DMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int r = matrix.length;
        int c = matrix[0].length;
        int left = 0;
        int right = r * c - 1;

        if (matrix[right / c][right % c] < target || matrix[0][0] > target)
            return false;
        else if (matrix[right / c][right % c] == target || matrix[0][0] == target)
            return true;

        while (left < right) {
            int idx = (left + right) / 2;
            if (matrix[idx / c][idx % c] < target)
                left = idx + 1;
            else if (matrix[idx / c][idx % c] > target) right = idx;
            else return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println(searchMatrix(matrix, 13));
    }
}
