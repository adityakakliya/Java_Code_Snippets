class Solution {
    public int findMin(int[] nums) {
        return nums[findPivot(nums)];
    }

    private int findPivot(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = (start + end)/2;
            if (mid-1 >= 0 && nums[mid] < nums[mid-1] && mid+1 < nums.length && nums[mid] < nums[mid+1]) {
                return mid;
            }
            if (mid + 1 < nums.length && nums[mid] > nums[mid+1]) {
                return mid+1;
            }
            if (nums[mid] > nums[start]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return 0;
    }
}