class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int m = coins.length;

        // Precompute LCM for every subset of coins.
        long[] lcm = new long[1 << m];

        lcm[0] = 1;

        for (int mask = 1; mask < (1 << m); mask++) {
            int bit = Integer.numberOfTrailingZeros(mask);
            int prev = mask & (mask - 1);

            long a = lcm[prev];
            long b = coins[bit];

            long g = gcd(a, b);
            lcm[mask] = a / g * b;
        }

        // The kth answer can never be larger than
        // k * the smallest denomination.
        long low = 1;
        long high = (long) k * getMin(coins);

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid, lcm, m) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, long[] lcm, int m) {
        long total = 0;

        for (int mask = 1; mask < (1 << m); mask++) {
            long value = lcm[mask];

            if (value > x) {
                continue;
            }

            long multiples = x / value;

            // Odd number of coins -> add
            // Even number of coins -> subtract
            if (Integer.bitCount(mask) % 2 == 1) {
                total += multiples;
            } else {
                total -= multiples;
            }
        }

        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private int getMin(int[] coins) {
        int min = coins[0];

        for (int coin : coins) {
            min = Math.min(min, coin);
        }

        return min;
    }
}