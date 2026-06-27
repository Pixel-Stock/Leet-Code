import java.util.*;

class Solution {
public List<List<Integer>> fourSum(int[] nums, int target) {
List<List<Integer>> res = new ArrayList<>();
int n = nums.length;
if (n < 4) return res;

    Arrays.sort(nums);

    for (int i = 0; i < n - 3; i++) {
        if (i > 0 && nums[i] == nums[i - 1]) continue;

        long min1 = (long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
        if (min1 > target) break;

        long max1 = (long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3];
        if (max1 < target) continue;

        for (int j = i + 1; j < n - 2; j++) {
            if (j > i + 1 && nums[j] == nums[j - 1]) continue;

            long min2 = (long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
            if (min2 > target) break;

            long max2 = (long) nums[i] + nums[j] + nums[n - 1] + nums[n - 2];
            if (max2 < target) continue;

            int left = j + 1;
            int right = n - 1;

            while (left < right) {
                long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }

    return res;
}

}