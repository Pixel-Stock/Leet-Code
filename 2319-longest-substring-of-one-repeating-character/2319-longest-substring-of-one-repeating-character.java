class Solution {

    int[] leftChar;
    int[] rightChar;
    int[] prefix;
    int[] suffix;
    int[] best;
    int[] length;

    char[] str;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        str = s.toCharArray();

        int n = str.length;
        int size = 4 * n;

        leftChar = new int[size];
        rightChar = new int[size];
        prefix = new int[size];
        suffix = new int[size];
        best = new int[size];
        length = new int[size];

        build(1, 0, n - 1);

        int[] answer = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];
            char c = queryCharacters.charAt(i);

            str[index] = c;

            update(1, 0, n - 1, index);

            answer[i] = best[1];
        }

        return answer;
    }

    private void build(int node, int l, int r) {

        if (l == r) {
            int c = str[l];

            leftChar[node] = c;
            rightChar[node] = c;

            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;
            length[node] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node);
    }

    private void update(int node, int l, int r, int index) {

        if (l == r) {

            int c = str[index];

            leftChar[node] = c;
            rightChar[node] = c;

            prefix[node] = 1;
            suffix[node] = 1;
            best[node] = 1;
            length[node] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        merge(node);
    }

    private void merge(int node) {

        int left = node * 2;
        int right = node * 2 + 1;

        length[node] = length[left] + length[right];

        leftChar[node] = leftChar[left];
        rightChar[node] = rightChar[right];

        // Prefix
        prefix[node] = prefix[left];

        if (prefix[left] == length[left]
                && rightChar[left] == leftChar[right]) {

            prefix[node] = length[left] + prefix[right];
        }

        // Suffix
        suffix[node] = suffix[right];

        if (suffix[right] == length[right]
                && rightChar[left] == leftChar[right]) {

            suffix[node] = length[right] + suffix[left];
        }

        // Best answer inside this segment
        best[node] = Math.max(best[left], best[right]);

        // A repeating sequence can cross the middle
        if (rightChar[left] == leftChar[right]) {

            best[node] = Math.max(
                best[node],
                suffix[left] + prefix[right]
            );
        }
    }
}
