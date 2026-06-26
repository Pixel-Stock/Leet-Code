class Solution {
    public int lengthOfLongestSubstring(String s) {

        int[] last = new int[128];

        int left = 0;
        int ans = 0;

        for (int right = 0; right < s.length(); right++) {

            char c = s.charAt(right);

            left = Math.max(left, last[c]);

            ans = Math.max(ans, right - left + 1);

            last[c] = right + 1;
        }

        return ans;
    }
}