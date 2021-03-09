class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(num -> {
            map.put(num, map.getOrDefault(num, 0) + 1);
        });
        List<List<Integer>> permuteUnique = new ArrayList<>();
        
        permuteUnique(map, permuteUnique, nums.length, new int[nums.length], 0);
        
        return permuteUnique;
    }
    
    private void permuteUnique (Map<Integer, Integer> map, List<List<Integer>> permuteUnique, int remaining, int[] nums, int index) {
        if (remaining == 0) {
            permuteUnique.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        } else {
            map.keySet().stream()
                .filter(key -> map.get(key) > 0)
                .forEach(key -> {
                    int count = map.get(key);
                    map.put(key, count-1);
                    nums[index] = key;
                    permuteUnique(map, permuteUnique, remaining - 1, nums, index + 1);
                    map.put(key, count);
            });
        }
    }
}
