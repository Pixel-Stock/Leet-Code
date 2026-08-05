import java.util.*;

class Solution {
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int stick : matchsticks) {
            sum += stick;
        }

        if (sum % 4 != 0) return false;

        int side = sum / 4;

        Arrays.sort(matchsticks);
        reverse(matchsticks);

        int[] sides = new int[4];
        return backtrack(matchsticks, 0, sides, side);
    }

    private boolean backtrack(int[] matchsticks, int index, int[] sides, int target) {
        if (index == matchsticks.length) {
            return sides[0] == target &&
                   sides[1] == target &&
                   sides[2] == target &&
                   sides[3] == target;
        }

        int stick = matchsticks[index];

        for (int i = 0; i < 4; i++) {
            if (sides[i] + stick > target) continue;

            sides[i] += stick;

            if (backtrack(matchsticks, index + 1, sides, target)) {
                return true;
            }

            sides[i] -= stick;

            if (sides[i] == 0) break;
        }

        return false;
    }

    private void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}