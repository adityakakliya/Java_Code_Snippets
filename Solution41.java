class Solution {
    public int firstMissingPositive(int[] nums) {
        BitSet bit = new BitSet(nums.length);
        for (int n : nums) {
            if (n > 0 && n <= nums.length) {
                bit.set(n);
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!bit.get(i)) {
                return i;
            }
        }
        return nums.length + 1;
    }
}