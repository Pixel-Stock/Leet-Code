class Solution {
    public int splitArray(int[] nums, int k) {
        long left = 0;
        long right = 0;

        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (canSplit(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return (int) left;
    }

    private boolean canSplit(int[] nums, int k, long maxSum) {
        int count = 1;
        long sum = 0;

        for (int num : nums) {
            if (sum + num > maxSum) {
                count++;
                sum = num;
                if (count > k) return false;
            } else {
                sum += num;
            }
        }

        return true;
    }
}
