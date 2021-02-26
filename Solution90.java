class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDup(nums, 0, result, new ArrayList<>());
        return result;
    }

    private void subsetsWithDup (int[] nums, int startIndex, List<List<Integer>> result, List<Integer> currentList) {
        result.add(new ArrayList<>(currentList));
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i-1]) {
                continue;
            }
            currentList.add(nums[i]);
            subsetsWithDup(nums, i+1, result, currentList);
            currentList.remove(currentList.size() - 1);
        }
    }
}