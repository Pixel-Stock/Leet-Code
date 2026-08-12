import java.util.*;

class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> gaps = new HashMap<>();

        for (List<Integer> row : wall) {
            int position = 0;

            // Do not count the edge at the end of the row.
            for (int i = 0; i < row.size() - 1; i++) {
                position += row.get(i);

                gaps.put(position, gaps.getOrDefault(position, 0) + 1);
            }
        }

        int maxGap = 0;

        for (int count : gaps.values()) {
            maxGap = Math.max(maxGap, count);
        }

        return wall.size() - maxGap;
    }
}