class Solution {
    public int myAtoi(String s) {

        int i = 0;
        int n = s.length();

        // Skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Check if string is empty after spaces
        if (i == n)
            return 0;

        // Check sign
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            if (s.charAt(i) == '-')
                sign = -1;
            i++;
        }

        int result = 0;

        // Read digits
        while (i < n && Character.isDigit(s.charAt(i))) {

            int digit = s.charAt(i) - '0';

            // Overflow check
            if (result > Integer.MAX_VALUE / 10 ||
               (result == Integer.MAX_VALUE / 10 &&
                digit > Integer.MAX_VALUE % 10)) {

                return sign == 1
                        ? Integer.MAX_VALUE
                        : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return sign * result;
    }
}