class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        int[][] dp = new int[n][n + 1];

        return dfs(0, 1, piles, suffix, dp);
    }

    private int dfs(int i, int m, int[] piles, int[] suffix, int[][] dp) {
        if (i >= piles.length) {
            return 0;
        }

        if (2 * m >= piles.length - i) {
            return suffix[i];
        }

        if (dp[i][m] != 0) {
            return dp[i][m];
        }

        int best = 0;

        for (int x = 1; x <= 2 * m; x++) {
            best = Math.max(best,
                    suffix[i] - dfs(i + x, Math.max(m, x), piles, suffix, dp));
        }

        dp[i][m] = best;
        return best;
    }
}