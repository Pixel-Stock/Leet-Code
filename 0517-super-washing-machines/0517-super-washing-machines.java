class Solution {
    public int findMinMoves(int[] machines) {
        int total = 0;

        for (int dresses : machines) {
            total += dresses;
        }

        int n = machines.length;

        if (total % n != 0) {
            return -1;
        }

        int target = total / n;
        int ans = 0;
        int balance = 0;

        for (int dresses : machines) {
            int diff = dresses - target;
            balance += diff;
            ans = Math.max(ans, Math.max(Math.abs(balance), diff));
        }

        return ans;
    }
}