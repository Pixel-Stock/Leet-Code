import java.util.*;

class Solution {
    public int findPairs(int[] nums, int k) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> pairs = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num - k)) {
                pairs.add(num);
            }

            if (seen.contains(num + k)) {
                pairs.add(num + k);
            }

            seen.add(num);
        }

        return pairs.size();
    }
}