class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        n = len(s)
        left = 0
        ones = 0
        best = None
        best_len = n + 1

        for right in range(n):
            if s[right] == '1':
                ones += 1

            if ones < k:
                continue

            while s[left] == '0':
                left += 1

            length = right - left + 1

            if length < best_len:
                best_len = length
                best = s[left:right + 1]

            elif length == best_len:
                candidate = s[left:right + 1]
                if candidate < best:
                    best = candidate

            ones -= 1
            left += 1

        return best if best is not None else ""