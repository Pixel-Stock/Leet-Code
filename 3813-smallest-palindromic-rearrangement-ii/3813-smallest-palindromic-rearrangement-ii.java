class Solution {
    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        String mid = "";
        int[] half = new int[26];
        int m = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) mid = String.valueOf((char) ('a' + i));
            half[i] = cnt[i] / 2;
            m += half[i];
        }

        if (countWays(half, m) < k) return "";

        StringBuilder left = new StringBuilder();

        while (m > 0) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;
                long ways = countWays(half, m - 1);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    m--;
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder right = new StringBuilder(left).reverse();
        return left.toString() + mid + right.toString();
    }

    private long countWays(int[] half, int len) {
        long res = 1;
        int rem = len;

        for (int i = 0; i < 26; i++) {
            int c = half[i];
            if (c == 0) continue;

            res *= comb(rem, c);
            if (res > LIMIT) res = LIMIT;
            rem -= c;
        }

        return res;
    }

    private long comb(int n, int r) {
        if (r > n) return 0;
        r = Math.min(r, n - r);

        long ans = 1;
        for (int i = 1; i <= r; i++) {
            ans = ans * (n - r + i) / i;
            if (ans > LIMIT) return LIMIT;
        }

        return ans;
    }
}