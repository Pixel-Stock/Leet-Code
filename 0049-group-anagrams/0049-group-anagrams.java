import java.util.*;

class Solution {
public List<List<String>> groupAnagrams(String[] strs) {
Map<String, List<String>> groups = new HashMap<>();

    for (String s : strs) {
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        StringBuilder key = new StringBuilder();

        for (int count : freq) {
            key.append('#').append(count);
        }

        groups.computeIfAbsent(key.toString(), k -> new ArrayList<>()).add(s);
    }

    return new ArrayList<>(groups.values());
}

}