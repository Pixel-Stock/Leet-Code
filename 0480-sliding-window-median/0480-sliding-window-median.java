import java.util.*;

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        TreeSet<Integer> left = new TreeSet<>((a, b) -> {
            if (nums[a] != nums[b]) return Integer.compare(nums[a], nums[b]);
            return Integer.compare(a, b);
        });

        TreeSet<Integer> right = new TreeSet<>((a, b) -> {
            if (nums[a] != nums[b]) return Integer.compare(nums[a], nums[b]);
            return Integer.compare(a, b);
        });

        for (int i = 0; i < k; i++) {
            left.add(i);
        }

        balance(left, right);

        double[] ans = new double[nums.length - k + 1];

        for (int i = k; ; i++) {
            if ((k & 1) == 1) {
                ans[i - k] = nums[left.last()];
            } else {
                ans[i - k] = ((long) nums[left.last()] + (long) nums[right.first()]) / 2.0;
            }

            if (i == nums.length) {
                break;
            }

            if (!left.remove(i - k)) {
                right.remove(i - k);
            }

            if (left.isEmpty() || nums[i] <= nums[left.last()]) {
                left.add(i);
            } else {
                right.add(i);
            }

            balance(left, right);
        }

        return ans;
    }

    private void balance(TreeSet<Integer> left, TreeSet<Integer> right) {
        while (left.size() > right.size() + 1) {
            right.add(left.pollLast());
        }

        while (left.size() < right.size()) {
            left.add(right.pollFirst());
        }
    }
}