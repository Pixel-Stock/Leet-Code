class Solution {
public boolean isMatch(String s, String p) {
int i = 0, j = 0;
int star = -1, match = 0;

    while (i < s.length()) {
        if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
            i++;
            j++;
        } else if (j < p.length() && p.charAt(j) == '*') {
            star = j;
            match = i;
            j++;
        } else if (star != -1) {
            j = star + 1;
            match++;
            i = match;
        } else {
            return false;
        }
    }

    while (j < p.length() && p.charAt(j) == '*') {
        j++;
    }

    return j == p.length();
}

}