import java.util.*;

class Solution {
public List<Integer> sequentialDigits(int low, int high) {
List<Integer> result = new ArrayList<>();
String digits = "123456789";

    int minLen = String.valueOf(low).length();
    int maxLen = String.valueOf(high).length();

    for (int len = minLen; len <= maxLen; len++) {
        for (int start = 0; start + len <= 9; start++) {
            int num = Integer.parseInt(digits.substring(start, start + len));
            if (num >= low && num <= high) {
                result.add(num);
            }
        }
    }

    return result;
}

}