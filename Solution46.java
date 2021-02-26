class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteUnique(nums, 0, result);
        return result;
    }

    private void permuteUnique(int[] nums, int startIndex, List<List<Integer>> result) {
        if (startIndex == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int i : nums) {
                list.add(i);
            }
            result.add(list);
        }

        for (int i = startIndex; i < nums.length; i++) {
            swap(nums, i, startIndex);
            permuteUnique(nums, startIndex + 1, result);
            swap(nums, i, startIndex);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int saved = nums[i];
        nums[i] = nums[j];
        nums[j] = saved;
    }
}