import java.util.*;

class Solution {
public List<String> fullJustify(String[] words, int maxWidth) {
List<String> result = new ArrayList<>();
int i = 0;

    while (i < words.length) {
        int j = i + 1;
        int lineLength = words[i].length();

        while (j < words.length &&
               lineLength + 1 + words[j].length() <= maxWidth) {
            lineLength += 1 + words[j].length();
            j++;
        }

        int wordCount = j - i;
        StringBuilder line = new StringBuilder();

        if (j == words.length || wordCount == 1) {
            for (int k = i; k < j; k++) {
                if (k > i) {
                    line.append(' ');
                }
                line.append(words[k]);
            }

            while (line.length() < maxWidth) {
                line.append(' ');
            }
        } else {
            int totalWordLength = 0;

            for (int k = i; k < j; k++) {
                totalWordLength += words[k].length();
            }

            int totalSpaces = maxWidth - totalWordLength;
            int gaps = wordCount - 1;
            int spacesPerGap = totalSpaces / gaps;
            int extraSpaces = totalSpaces % gaps;

            for (int k = i; k < j; k++) {
                line.append(words[k]);

                if (k < j - 1) {
                    int spaces = spacesPerGap;

                    if (k - i < extraSpaces) {
                        spaces++;
                    }

                    for (int s = 0; s < spaces; s++) {
                        line.append(' ');
                    }
                }
            }
        }

        result.add(line.toString());
        i = j;
    }

    return result;
}

}