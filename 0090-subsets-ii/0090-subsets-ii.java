import java.util.*;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>(1 << nums.length);
        dfs(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int[] nums, int index, List<Integer> curr, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(curr));

        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1]) continue;

            curr.add(nums[i]);
            dfs(nums, i + 1, curr, ans);
            curr.remove(curr.size() - 1);
        }
    }
}