import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();

        for (int[] b : buildings) {
            events.add(new int[]{b[0], -b[2]});
            events.add(new int[]{b[1], b[2]});
        }

        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        List<List<Integer>> result = new ArrayList<>();
        TreeMap<Integer, Integer> heights = new TreeMap<>();
        heights.put(0, 1);

        int prevMax = 0;

        for (int[] event : events) {
            int x = event[0];
            int h = event[1];

            if (h < 0) {
                h = -h;
                heights.put(h, heights.getOrDefault(h, 0) + 1);
            } else {
                int count = heights.get(h);
                if (count == 1) {
                    heights.remove(h);
                } else {
                    heights.put(h, count - 1);
                }
            }

            int currMax = heights.lastKey();

            if (currMax != prevMax) {
                result.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return result;
    }
}