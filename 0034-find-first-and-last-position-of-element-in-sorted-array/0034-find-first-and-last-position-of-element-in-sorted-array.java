class Solution {
public int[] searchRange(int[] nums, int target) {
return new int[]{findFirst(nums, target), findLast(nums, target)};
}

private int findFirst(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    int ans = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] >= target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }

        if (nums[mid] == target) {
            ans = mid;
        }
    }

    return ans;
}

private int findLast(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    int ans = -1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] <= target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }

        if (nums[mid] == target) {
            ans = mid;
        }
    }

    return ans;
}

}