import java.util.*;

class Solution {

    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;

        if (n <= 1) {
            return trees;
        }

        Arrays.sort(trees, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        List<int[]> hull = new ArrayList<>();

        // Lower hull
        for (int[] p : trees) {
            while (hull.size() >= 2 &&
                   cross(hull.get(hull.size() - 2),
                         hull.get(hull.size() - 1),
                         p) < 0) {
                hull.remove(hull.size() - 1);
            }

            hull.add(p);
        }

        // Upper hull
        int lowerSize = hull.size();

        for (int i = trees.length - 2; i >= 0; i--) {
            int[] p = trees[i];

            while (hull.size() > lowerSize &&
                   cross(hull.get(hull.size() - 2),
                         hull.get(hull.size() - 1),
                         p) < 0) {
                hull.remove(hull.size() - 1);
            }

            hull.add(p);
        }

        // Remove duplicate first/last point
        hull.remove(hull.size() - 1);

        // Remove duplicate points caused by collinear cases
        Set<String> seen = new HashSet<>();
        List<int[]> result = new ArrayList<>();

        for (int[] p : hull) {
            String key = p[0] + "," + p[1];

            if (seen.add(key)) {
                result.add(p);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    private int cross(int[] a, int[] b, int[] c) {
        return (b[0] - a[0]) * (c[1] - a[1])
             - (b[1] - a[1]) * (c[0] - a[0]);
    }
}