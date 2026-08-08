class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        // maxStart[j] = the LARGEST index i such that word2[j:] is an exact
        // subsequence of word1[i:]. (-1 if impossible for any i)
        // word2[j:] is a subsequence of word1[i:]  <=>  i <= maxStart[j]
        int[] maxStart = new int[m + 1];
        java.util.Arrays.fill(maxStart, -1);
        maxStart[m] = n; // empty suffix always matches

        int k = m - 1;
        for (int i = n - 1; i >= 0 && k >= 0; i--) {
            if (word1.charAt(i) == word2.charAt(k)) {
                maxStart[k] = i;
                k--;
            }
        }

        int[] result = new int[m];
        int i = 0, j = 0;
        boolean changed = false;

        while (j < m) {
            if (i >= n) break;

            if (word1.charAt(i) == word2.charAt(j)) {
                // Exact match: always take it now, greedily minimizes this index.
                result[j] = i;
                i++; j++;
            } else if (!changed && maxStart[j + 1] >= i + 1) {
                // Use our one allowed change here, only if the rest of word2
                // can still be matched exactly using word1[i+1:].
                result[j] = i;
                changed = true;
                i++; j++;
            } else {
                // Can't use it here (either change already spent, or using it
                // would make the remainder infeasible) -> skip this char.
                i++;
            }
        }

        if (j == m) return result;
        return new int[0];
    }
}