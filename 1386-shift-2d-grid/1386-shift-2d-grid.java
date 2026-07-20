import java.util.*;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        k %= total;

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            ans.add(new ArrayList<>());
        }

        for (int i = 0; i < total; i++) {
            int oldIndex = (i - k + total) % total;

            int oldRow = oldIndex / n;
            int oldCol = oldIndex % n;

            ans.get(i / n).add(grid[oldRow][oldCol]);
        }

        return ans;
    }
}