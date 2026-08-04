class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;

            boolean forward = nums[i] > 0;
            int slow = i, fast = i;

            while (true) {
                slow = getNext(nums, forward, slow);
                if (slow == -1) break;

                fast = getNext(nums, forward, fast);
                if (fast == -1) break;

                fast = getNext(nums, forward, fast);
                if (fast == -1) break;

                if (slow == fast) {
                    return true;
                }
            }

            int curr = i;
            while (true) {
                int next = getNext(nums, forward, curr);
                nums[curr] = 0;
                if (next == -1) break;
                curr = next;
            }
        }

        return false;
    }

    private int getNext(int[] nums, boolean forward, int curr) {
        boolean direction = nums[curr] > 0;
        if (direction != forward) return -1;

        int n = nums.length;
        int next = ((curr + nums[curr]) % n + n) % n;

        if (next == curr) return -1;

        if ((nums[next] > 0) != forward) return -1;

        return next;
    }
}