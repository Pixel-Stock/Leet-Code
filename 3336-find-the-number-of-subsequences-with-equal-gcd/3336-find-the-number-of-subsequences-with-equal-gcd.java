class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int MAX = 200;

        int[][] gcd = new int[MAX + 1][MAX + 1];
        for (int i = 0; i <= MAX; i++) {
            for (int j = 0; j <= MAX; j++) {
                if (i == 0) gcd[i][j] = j;
                else if (j == 0) gcd[i][j] = i;
                else {
                    int a = i, b = j;
                    while (b != 0) {
                        int t = a % b;
                        a = b;
                        b = t;
                    }
                    gcd[i][j] = a;
                }
            }
        }

        int[][] dp = new int[MAX + 1][MAX + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            int[][] next = new int[MAX + 1][MAX + 1];

            for (int g1 = 0; g1 <= MAX; g1++) {
                for (int g2 = 0; g2 <= MAX; g2++) {
                    int cur = dp[g1][g2];
                    if (cur == 0) continue;

                    next[g1][g2] = (next[g1][g2] + cur) % MOD;

                    int ng1 = gcd[g1][x];
                    next[ng1][g2] = (next[ng1][g2] + cur) % MOD;

                    int ng2 = gcd[g2][x];
                    next[g1][ng2] = (next[g1][ng2] + cur) % MOD;
                }
            }

            dp = next;
        }

        long ans = 0;
        for (int g = 1; g <= MAX; g++) {
            ans += dp[g][g];
        }

        return (int) (ans % MOD);
    }
}