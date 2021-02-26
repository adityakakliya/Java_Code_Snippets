class Solution {
    public int maxPower(String s) {
        int maxPower = 1;
        for (int left = 0, right = 1; right < s.length(); right++) {
            if (s.charAt(right) != s.charAt(left)) {
                left = right;
            }
            maxPower = Math.max(maxPower, right - left + 1);
        }

        return maxPower;
    }
}