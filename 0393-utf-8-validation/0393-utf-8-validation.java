class Solution {
    public boolean validUtf8(int[] data) {
        int remaining = 0;

        for (int num : data) {
            num &= 0xFF;

            if (remaining == 0) {
                if ((num >> 7) == 0) {
                    continue;
                } else if ((num >> 5) == 0b110) {
                    remaining = 1;
                } else if ((num >> 4) == 0b1110) {
                    remaining = 2;
                } else if ((num >> 3) == 0b11110) {
                    remaining = 3;
                } else {
                    return false;
                }
            } else {
                if ((num >> 6) != 0b10) {
                    return false;
                }
                remaining--;
            }
        }

        return remaining == 0;
    }
}