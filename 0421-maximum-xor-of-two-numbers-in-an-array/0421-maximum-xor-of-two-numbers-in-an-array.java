class Solution {
    static class TrieNode {
        TrieNode[] child = new TrieNode[2];
    }

    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();

        for (int num : nums) {
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.child[bit] == null) {
                    node.child[bit] = new TrieNode();
                }
                node = node.child[bit];
            }
        }

        int maxXor = 0;

        for (int num : nums) {
            TrieNode node = root;
            int currXor = 0;

            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                int opposite = 1 - bit;

                if (node.child[opposite] != null) {
                    currXor |= (1 << i);
                    node = node.child[opposite];
                } else {
                    node = node.child[bit];
                }
            }

            maxXor = Math.max(maxXor, currXor);
        }

        return maxXor;
    }
}