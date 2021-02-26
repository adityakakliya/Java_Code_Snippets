class Solution {
    public int removeDuplicates(int[] nums) {
        int uniqueNumberIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[uniqueNumberIndex]) {
                nums[uniqueNumberIndex + 1] = nums[i];
                uniqueNumberIndex++;
            }
        }
        return uniqueNumberIndex + 1;
    }
}