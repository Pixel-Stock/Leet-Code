class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = Integer.MIN_VALUE;

        if (m > n) {
            int[][] trans = new int[n][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    trans[j][i] = matrix[i][j];
                }
            }
            matrix = trans;
            m = matrix.length;
            n = matrix[0].length;
        }

        for (int left = 0; left < n; left++) {
            int[] rowSum = new int[m];

            for (int right = left; right < n; right++) {
                for (int i = 0; i < m; i++) {
                    rowSum[i] += matrix[i][right];
                }

                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);

                int prefix = 0;
                for (int sum : rowSum) {
                    prefix += sum;
                    Integer target = set.ceiling(prefix - k);
                    if (target != null) {
                        ans = Math.max(ans, prefix - target);
                    }
                    set.add(prefix);
                }
            }
        }

        return ans;
    }
}