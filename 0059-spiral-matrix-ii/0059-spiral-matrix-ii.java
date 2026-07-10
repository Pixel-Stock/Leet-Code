class Solution {
public int[][] generateMatrix(int n) {
int[][] matrix = new int[n][n];

    int top = 0;
    int bottom = n - 1;
    int left = 0;
    int right = n - 1;
    int value = 1;

    while (top <= bottom && left <= right) {
        for (int col = left; col <= right; col++) {
            matrix[top][col] = value++;
        }
        top++;

        for (int row = top; row <= bottom; row++) {
            matrix[row][right] = value++;
        }
        right--;

        if (top <= bottom) {
            for (int col = right; col >= left; col--) {
                matrix[bottom][col] = value++;
            }
            bottom--;
        }

        if (left <= right) {
            for (int row = bottom; row >= top; row--) {
                matrix[row][left] = value++;
            }
            left++;
        }
    }

    return matrix;
}

}