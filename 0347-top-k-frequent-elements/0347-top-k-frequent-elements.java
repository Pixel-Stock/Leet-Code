class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new ArrayList[nums.length + 1];

        for (int key : freq.keySet()) {
            int f = freq.get(key);
            if (bucket[f] == null) {
                bucket[f] = new ArrayList<>();
            }
            bucket[f].add(key);
        }

        int[] ans = new int[k];
        int idx = 0;

        for (int i = bucket.length - 1; i >= 0 && idx < k; i--) {
            if (bucket[i] != null) {
                for (int num : bucket[i]) {
                    ans[idx++] = num;
                    if (idx == k) {
                        break;
                    }
                }
            }
        }

        return ans;
    }
}