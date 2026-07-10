class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] order = new Integer[n];

    for (int i = 0; i < n; i++) {
        order[i] = i;
    }

    Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

    int[] values = new int[n];
    int[] position = new int[n];

    for (int i = 0; i < n; i++) {
        values[i] = nums[order[i]];
        position[order[i]] = i;
    }

    int[] component = new int[n];
    int comp = 0;

    for (int i = 1; i < n; i++) {
        if ((long) values[i] - values[i - 1] > maxDiff) {
            comp++;
        }
        component[i] = comp;
    }

    int[] next = new int[n];
    int right = 0;

    for (int i = 0; i < n; i++) {
        if (right < i) {
            right = i;
        }

        while (right + 1 < n &&
               (long) values[right + 1] - values[i] <= maxDiff) {
            right++;
        }

        next[i] = right;
    }

    int log = 1;
    while ((1 << log) <= n) {
        log++;
    }

    int[][] jump = new int[log][n];

    for (int i = 0; i < n; i++) {
        jump[0][i] = next[i];
    }

    for (int k = 1; k < log; k++) {
        for (int i = 0; i < n; i++) {
            jump[k][i] = jump[k - 1][jump[k - 1][i]];
        }
    }

    int[] answer = new int[queries.length];

    for (int q = 0; q < queries.length; q++) {
        int u = position[queries[q][0]];
        int v = position[queries[q][1]];

        if (u == v) {
            answer[q] = 0;
            continue;
        }

        int left = Math.min(u, v);
        int target = Math.max(u, v);

        if (component[left] != component[target]) {
            answer[q] = -1;
            continue;
        }

        int current = left;
        int distance = 0;

        for (int k = log - 1; k >= 0; k--) {
            if (jump[k][current] < target) {
                current = jump[k][current];
                distance += 1 << k;
            }
        }

        answer[q] = distance + 1;
    }

    return answer;
    }
}