import java.util.*;

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int ans = 0;

        Map<Long, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long diff = (long) nums[i] - nums[j];

                int count = dp[j].getOrDefault(diff, 0);
                ans += count;

                dp[i].put(diff, dp[i].getOrDefault(diff, 0) + count + 1);
            }
        }

        return ans;
    }
}