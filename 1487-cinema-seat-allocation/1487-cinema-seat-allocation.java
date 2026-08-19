import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> reserved = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Only seats 2 through 9 matter.
            if (col >= 2 && col <= 9) {
                int mask = reserved.getOrDefault(row, 0);
                mask |= (1 << col);
                reserved.put(row, mask);
            }
        }

        // Every completely empty row can fit 2 groups:
        // [2,3,4,5] and [6,7,8,9]
        long answer = 2L * n;

        for (int mask : reserved.values()) {
            boolean left = (mask & (1 << 2)) == 0
                    && (mask & (1 << 3)) == 0
                    && (mask & (1 << 4)) == 0
                    && (mask & (1 << 5)) == 0;

            boolean middle = (mask & (1 << 4)) == 0
                    && (mask & (1 << 5)) == 0
                    && (mask & (1 << 6)) == 0
                    && (mask & (1 << 7)) == 0;

            boolean right = (mask & (1 << 6)) == 0
                    && (mask & (1 << 7)) == 0
                    && (mask & (1 << 8)) == 0
                    && (mask & (1 << 9)) == 0;

            if (left && right) {
                // Can fit two groups.
            } else if (left || middle || right) {
                // Can fit exactly one group.
                answer--;
            } else {
                // Cannot fit any group.
                answer -= 2;
            }
        }

        return (int) answer;
    }
}