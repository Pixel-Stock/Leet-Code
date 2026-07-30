import java.util.*;

class RandomizedCollection {

    private List<Integer> list;
    private Map<Integer, Set<Integer>> map;
    private Random random;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean notPresent = !map.containsKey(val) || map.get(val).isEmpty();

        map.computeIfAbsent(val, k -> new HashSet<>()).add(list.size());
        list.add(val);

        return notPresent;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).isEmpty()) {
            return false;
        }

        Iterator<Integer> it = map.get(val).iterator();
        int removeIndex = it.next();
        it.remove();

        int lastIndex = list.size() - 1;
        int lastValue = list.get(lastIndex);

        if (removeIndex != lastIndex) {
            list.set(removeIndex, lastValue);

            Set<Integer> lastSet = map.get(lastValue);
            lastSet.remove(lastIndex);
            lastSet.add(removeIndex);
        }

        list.remove(lastIndex);

        if (map.get(val).isEmpty()) {
            map.remove(val);
        }

        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
