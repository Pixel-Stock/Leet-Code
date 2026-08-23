class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int leftSum = 0;
        int rightSum = 0;

        int leftQ = 0;
        int rightQ = 0;

        // First half
        for (int i = 0; i < half; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                leftQ++;
            } else {
                leftSum += c - '0';
            }
        }

        // Second half
        for (int i = half; i < n; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                rightQ++;
            } else {
                rightSum += c - '0';
            }
        }

        int totalQ = leftQ + rightQ;

        // Alice gets the last move.
        // She can always force the sums to be different.
        if (totalQ % 2 == 1) {
            return true;
        }

        /*
         * Bob can win only if the existing difference is
         * exactly compensated by the unmatched '?' characters.
         *
         * leftSum - rightSum
         *      ==
         * 9 * (rightQ - leftQ) / 2
         *
         * Multiply by 2 to avoid integer division.
         */
        int sumDiff = leftSum - rightSum;
        int questionDiff = rightQ - leftQ;

        return 2 * sumDiff != 9 * questionDiff;
    }
}