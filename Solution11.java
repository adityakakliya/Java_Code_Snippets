class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        for (int left = 0, right = height.length - 1; left < right;) {
            int prevHeight = Math.min(height[left], height[right]);

            maxArea = Math.max( prevHeight * (right - left), maxArea);

            if (prevHeight == height[left]) {
                while (left < right && height[left] <= prevHeight) {
                    left++;
                }
            } else {
                while (left < right && height[right] <= prevHeight) {
                    right--;
                }
            }
        }

        return maxArea;
    }
}