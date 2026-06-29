import java.util.*;

class Solution {
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
Arrays.sort(candidates);
List<List<Integer>> ans = new ArrayList<>();
backtrack(candidates, target, 0, new ArrayList<>(), ans);
return ans;
}

private void backtrack(int[] candidates, int target, int start,
                       List<Integer> curr, List<List<Integer>> ans) {
    if (target == 0) {
        ans.add(new ArrayList<>(curr));
        return;
    }

    for (int i = start; i < candidates.length; i++) {
        if (i > start && candidates[i] == candidates[i - 1]) {
            continue;
        }

        if (candidates[i] > target) {
            break;
        }

        curr.add(candidates[i]);
        backtrack(candidates, target - candidates[i], i + 1, curr, ans);
        curr.remove(curr.size() - 1);
    }
}

}