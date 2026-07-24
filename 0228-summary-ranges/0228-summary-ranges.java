class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        int i = 0;
        while (i < nums.length) {
            int start = nums[i];

            while (i + 1 < nums.length && (long) nums[i + 1] - nums[i] == 1) {
                i++;
            }

            if (start == nums[i]) {
                result.add(String.valueOf(start));
            } else {
                result.add(start + "->" + nums[i]);
            }

            i++;
        }

        return result;
    }
}