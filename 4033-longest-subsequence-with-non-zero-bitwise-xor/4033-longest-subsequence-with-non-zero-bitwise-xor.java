class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        // If the XOR of all elements is non-zero,
        // taking the entire array is optimal.
        if (xor != 0) {
            return nums.length;
        }

        // Total XOR is zero.
        // If every element is zero, no non-empty subsequence
        // can have non-zero XOR.
        for (int num : nums) {
            if (num != 0) {
                return nums.length - 1;
            }
        }

        return 0;
    }
}