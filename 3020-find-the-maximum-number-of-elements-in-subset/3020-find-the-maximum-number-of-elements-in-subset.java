import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int x : nums) {
            freq.put((long) x, freq.getOrDefault((long) x, 0) + 1);
        }

        int ans = 1;

        if (freq.containsKey(1L)) {
            int c = freq.get(1L);
            ans = Math.max(ans, (c % 2 == 1) ? c : c - 1);
        }

        for (long start : freq.keySet()) {
            if (start == 1L) continue;

            long cur = start;
            int len = 0;

            while (true) {
                Integer cnt = freq.get(cur);

                if (cnt == null) {
                    len--;
                    break;
                }

                if (cnt == 1) {
                    len++;
                    break;
                }

                len += 2;

                if (cur > 1000000000L / cur) {
                    len--;
                    break;
                }

                cur *= cur;
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}