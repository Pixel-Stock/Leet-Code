import java.util.*;

class Solution {
    public int[] resultArray(int[] nums) {

        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        // First two operations
        arr1.add(nums[0]);
        arr2.add(nums[1]);

        // Remaining elements
        for (int i = 2; i < nums.length; i++) {

            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }

        // Combine arr1 + arr2
        int[] result = new int[nums.length];
        int index = 0;

        for (int num : arr1) {
            result[index++] = num;
        }

        for (int num : arr2) {
            result[index++] = num;
        }

        return result;
    }
}