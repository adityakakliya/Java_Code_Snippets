class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> fourSum = new ArrayList<>();
        int n = nums.length;
        for (int aIndex = 0; aIndex < n - 3; aIndex++) {
            for (int dIndex = n-1; dIndex > aIndex + 2; dIndex--) {
                int bIndex = aIndex + 1;
                int cIndex = dIndex - 1;
                int rem = target - nums[aIndex] - nums[dIndex];
                while (bIndex < cIndex) {
                    int sum = nums[cIndex] + nums[bIndex];
                    if (sum < rem) {
                        bIndex++;
                    } else if (sum > rem) {
                        cIndex--;
                    } else {
                        fourSum.add(Arrays.asList(nums[aIndex], nums[bIndex], nums[cIndex], nums[dIndex]));
                        bIndex++;
                        cIndex--;
                        while (cIndex > bIndex && nums[bIndex] == nums[bIndex-1]) {
                            bIndex++;
                        }
                        while (cIndex > bIndex && nums[cIndex] == nums[cIndex+1]) {
                            cIndex--;
                        }
                    }
                }
                while (dIndex > aIndex + 2 && nums[dIndex] == nums[dIndex - 1]) {
                    dIndex--;
                }
            }
            while (aIndex < n - 3 && nums[aIndex] == nums[aIndex+1]) {
                aIndex++;
            }
        }

        return fourSum;

    }
}