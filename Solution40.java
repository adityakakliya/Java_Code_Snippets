class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combinationSum(candidates, 0, target, result, new ArrayList<>());
        return result;
    }

    private void combinationSum(int[] candidates, int start, int target, List<List<Integer>> result, List<Integer> list) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i-1]) {
                continue;
            }
            if (target - candidates[i] < 0) {
                break;
            }
            list.add(candidates[i]);
            combinationSum(candidates, i + 1, target - candidates[i], result, list);
            list.remove(list.size() - 1);
        }
    }
}