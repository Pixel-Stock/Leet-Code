import java.util.*;

class Solution {
    public int distributeCandies(int[] candyType) {
        int n = candyType.length;

        Set<Integer> types = new HashSet<>();

        for (int candy : candyType) {
            types.add(candy);
        }

        return Math.min(types.size(), n / 2);
    }
}