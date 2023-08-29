package leet150.slidingwindow;

/**
 * bbb
 * pwwke
 * p w
 * w
 */

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwke"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;

        int left = 0;
        int right = 1;
        int max = Integer.MIN_VALUE;

        while (right <= s.length()) {
            boolean isDup = false;
            int i = 0;
            for (i = left; i < right; i++) {
                int idx = s.indexOf(s.charAt(i), i + 1);
                if (idx > 0 && idx < right) {
                    isDup = true;
                    break;
                }
            }
            if (isDup) left = i + 1;
            else {
                max = Math.max(max, right - left);
                right++;
            }
        }
        return max;
    }
}
