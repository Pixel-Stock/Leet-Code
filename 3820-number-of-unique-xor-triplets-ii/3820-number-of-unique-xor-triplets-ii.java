class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[] present = new boolean[MAX];
        for (int x : nums) {
            present[x] = true;
        }

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int step = 1; step <= 3; step++) {
            for (int xor = 0; xor < MAX; xor++) {
                if (!dp[step - 1][xor]) continue;

                for (int val = 0; val < MAX; val++) {
                    if (present[val]) {
                        dp[step][xor ^ val] = true;
                    }
                }
            }
        }

        int ans = 0;
        for (boolean b : dp[3]) {
            if (b) ans++;
        }

        return ans;
    }
}