class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSum3(k, n, result, new ArrayList<>(), 0);
        return result;
    }

    private void combinationSum3 (int k, int n, List<List<Integer>> result, List<Integer> list, int startIndex) {
        if (list.size() == k) {
            if (n == 0) {
                result.add(new ArrayList<>(list));
            }
            return;
        }

        int[] nums = new int[]{1,2,3,4,5,6,7,8,9};
        for (int i = startIndex; i < nums.length; i++) {
            list.add(nums[i]);
            combinationSum3(k, n - nums[i], result, list, i+1);
            list.remove(list.size() - 1);
        }
    }
}