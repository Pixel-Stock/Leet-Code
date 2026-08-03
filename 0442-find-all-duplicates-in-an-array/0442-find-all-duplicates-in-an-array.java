class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for (int num : nums) {
            int idx = Math.abs(num) - 1;

            if (nums[idx] < 0) {
                ans.add(idx + 1);
            } else {
                nums[idx] = -nums[idx];
            }
        }

        return ans;
    }
}