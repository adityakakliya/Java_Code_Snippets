class Solution {
    public int peakIndexInMountainArray(int[] nums) {
        int startIndex = 0;
        int endIndex = nums.length - 1;

        while (startIndex <= endIndex) {
            int mid = (startIndex + endIndex) / 2;

            if (mid-1 >= 0 && nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) {
                return mid;
            } else if (nums[mid] < nums[mid+1]) {
                startIndex = mid + 1;
            } else if (nums[mid] > nums[mid+1]) {
                endIndex = mid - 1;
            }
        }
        return -1;
    }
}