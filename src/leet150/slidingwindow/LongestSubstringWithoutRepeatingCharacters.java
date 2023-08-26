package leet150.slidingwindow;


public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) return 0;

        int left = 0;
        int right = 1;
        int max = Integer.MIN_VALUE;

        while (right <= s.length()) {
            String temp = s.substring(left, right);
            boolean isDuplicated = false;
            for (int i = 0; i < temp.length(); i++) {
                if (temp.indexOf(temp.charAt(i), i + 1) > 0){
                    isDuplicated = true;
                }
            }
            if(isDuplicated) left++;
            else {
                right++;
                max = Math.max(max, temp.length());
            }
        }
        return max;
    }
}
