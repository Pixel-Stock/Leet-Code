import java.util.*;

class Solution {
public List<List<String>> solveNQueens(int n) {
List<List<String>> ans = new ArrayList<>();
char[][] board = new char[n][n];
    for (char[] row : board) {
        Arrays.fill(row, '.');
    }

    boolean[] cols = new boolean[n];
    boolean[] diag1 = new boolean[2 * n - 1];
    boolean[] diag2 = new boolean[2 * n - 1];

    backtrack(0, n, board, cols, diag1, diag2, ans);
    return ans;
}

private void backtrack(int row, int n, char[][] board,
                       boolean[] cols, boolean[] diag1,
                       boolean[] diag2, List<List<String>> ans) {
    if (row == n) {
        List<String> solution = new ArrayList<>();
        for (char[] r : board) {
            solution.add(new String(r));
        }
        ans.add(solution);
        return;
    }

    for (int col = 0; col < n; col++) {
        int d1 = row - col + n - 1;
        int d2 = row + col;

        if (cols[col] || diag1[d1] || diag2[d2]) {
            continue;
        }

        board[row][col] = 'Q';
        cols[col] = true;
        diag1[d1] = true;
        diag2[d2] = true;

        backtrack(row + 1, n, board, cols, diag1, diag2, ans);

        board[row][col] = '.';
        cols[col] = false;
        diag1[d1] = false;
        diag2[d2] = false;
    }
}
}
