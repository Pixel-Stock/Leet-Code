class Solution {
    int[] e2 = new int[10], e3 = new int[10], e5 = new int[10], e7 = new int[10];
    int[][] minAB; 
    int A, B, C, D;

    public String smallestNumber(String num, long t) {
        long tt = t;
        int a = 0, b = 0, c = 0, d = 0;
        while (tt % 2 == 0) { tt /= 2; a++; }
        while (tt % 3 == 0) { tt /= 3; b++; }
        while (tt % 5 == 0) { tt /= 5; c++; }
        while (tt % 7 == 0) { tt /= 7; d++; }
        if (tt != 1) return "-1"; 
        A = a; B = b; C = c; D = d;

        e2[2]=1; e2[4]=2; e2[6]=1; e2[8]=3;
        e3[3]=1; e3[6]=1; e3[9]=2;
        e5[5]=1;
        e7[7]=1;

        minAB = new int[A+1][B+1];
        for (int aa = 0; aa <= A; aa++) {
            for (int bb = 0; bb <= B; bb++) {
                int best = Integer.MAX_VALUE;
                int kmax = Math.min(aa, bb);
                for (int k = 0; k <= kmax; k++) {
                    int val = k + ceilDiv(aa - k, 3) + ceilDiv(bb - k, 2);
                    if (val < best) best = val;
                }
                minAB[aa][bb] = best;
            }
        }

        int n = num.length();
        char[] digits = num.toCharArray();

        boolean[] prefixOk = new boolean[n + 1];
        prefixOk[0] = true;
        for (int i = 0; i < n; i++) prefixOk[i + 1] = prefixOk[i] && digits[i] != '0';

        int[] pe2 = new int[n + 1], pe3 = new int[n + 1], pe5 = new int[n + 1], pe7 = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int dg = digits[i] - '0';
            int x2 = (dg >= 1) ? e2[dg] : 0;
            int x3 = (dg >= 1) ? e3[dg] : 0;
            int x5 = (dg >= 1) ? e5[dg] : 0;
            int x7 = (dg >= 1) ? e7[dg] : 0;
            pe2[i+1] = pe2[i] + x2;
            pe3[i+1] = pe3[i] + x3;
            pe5[i+1] = pe5[i] + x5;
            pe7[i+1] = pe7[i] + x7;
        }

        if (prefixOk[n] && pe2[n] >= A && pe3[n] >= B && pe5[n] >= C && pe7[n] >= D) {
            return num;
        }

        for (int i = n - 1; i >= 0; i--) {
            if (!prefixOk[i]) continue;
            int ra = Math.max(0, A - pe2[i]);
            int rb = Math.max(0, B - pe3[i]);
            int rc = Math.max(0, C - pe5[i]);
            int rd = Math.max(0, D - pe7[i]);
            int origDigit = digits[i] - '0';
            int remainingLen = n - i - 1;

            for (int dig = origDigit + 1; dig <= 9; dig++) {
                int na = Math.max(0, ra - e2[dig]);
                int nb = Math.max(0, rb - e3[dig]);
                int nc = Math.max(0, rc - e5[dig]);
                int nd = Math.max(0, rd - e7[dig]);
                int need = nc + nd + minAB[na][nb];
                if (need <= remainingLen) {
                    StringBuilder res = new StringBuilder();
                    res.append(digits, 0, i);
                    res.append((char) ('0' + dig));
                    res.append(fillSuffix(remainingLen, na, nb, nc, nd));
                    return res.toString();
                }
            }
        }

        int fullMin = C + D + minAB[A][B];
        int L = Math.max(n + 1, fullMin);
        return fillSuffix(L, A, B, C, D);
    }

    private int ceilDiv(int x, int y) {
        if (x <= 0) return 0;
        return (x + y - 1) / y;
    }

    private String fillSuffix(int len, int a, int b, int c, int d) {
        StringBuilder sb = new StringBuilder();
        int ra = a, rb = b, rc = c, rd = d;
        for (int pos = 0; pos < len; pos++) {
            int remAfter = len - pos - 1;
            for (int dig = 1; dig <= 9; dig++) {
                int na = Math.max(0, ra - e2[dig]);
                int nb = Math.max(0, rb - e3[dig]);
                int nc = Math.max(0, rc - e5[dig]);
                int nd = Math.max(0, rd - e7[dig]);
                int need = nc + nd + minAB[na][nb];
                if (need <= remAfter) {
                    sb.append((char) ('0' + dig));
                    ra = na; rb = nb; rc = nc; rd = nd;
                    break;
                }
            }
        }
        return sb.toString();
    }
}