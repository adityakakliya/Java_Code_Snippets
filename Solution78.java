class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets(nums, 0, subsets, new ArrayList<>());
        return subsets;
    }

    private void subsets (int[] nums, int startIndex, List<List<Integer>> subsets, List<Integer> currentList) {
        subsets.add(new ArrayList<>(currentList));
        for (int i = startIndex; i < nums.length; i++) {
            currentList.add(nums[i]);
            subsets(nums, i + 1, subsets, currentList);
            currentList.remove(currentList.size() - 1);
        }
    }
}