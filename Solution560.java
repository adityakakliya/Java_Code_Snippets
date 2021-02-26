class Solution {
    public int subarraySum(int[] nums, int k) {
        int sol = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int prefixSum = 0;

        for (int num : nums) {
            prefixSum += num; // 1
            if (prefixSum == k) {
                sol++;
            }
            if (map.containsKey(prefixSum - k)) {
                sol += map.get(prefixSum - k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return sol;
    }
}