class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                ones++;
            }
        }

        String t = "1" + s + "1";
        int m = t.length();

        java.util.ArrayList<Character> type = new java.util.ArrayList<>();
        java.util.ArrayList<Integer> len = new java.util.ArrayList<>();

        int i = 0;
        while (i < m) {
            char ch = t.charAt(i);
            int j = i;
            while (j < m && t.charAt(j) == ch) {
                j++;
            }
            type.add(ch);
            len.add(j - i);
            i = j;
        }

        int ans = ones;

        for (int k = 1; k < type.size() - 1; k++) {
            if (type.get(k) == '1' && type.get(k - 1) == '0' && type.get(k + 1) == '0') {
                ans = Math.max(ans, ones + len.get(k - 1) + len.get(k + 1));
            }
        }

        return ans;
    }
}