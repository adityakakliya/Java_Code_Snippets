/*


a,b,c

fix a -> find b and c

all pairs -> sorted -> two pointers


if a[i] == a[i-1] -> all pairs would be same

avoid this



*/


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        final List<List<Integer>> threeSum = new ArrayList<>();

        if (nums.length < 3) {
            return threeSum;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            List<List<Integer>> twoSumPairs = findTwoSumPairs(nums, i+1, -nums[i]);
            for (List<Integer> twoSumPair : twoSumPairs) {
                List<Integer> triplet = new ArrayList<>();
                triplet.add(nums[i]);
                triplet.addAll(twoSumPair);
                threeSum.add(triplet);
            }
        }

        return threeSum;
    }

    private List<List<Integer>> findTwoSumPairs (int[] nums, int index, int sumRequired) {
        List<List<Integer>> twoSumPairs = new ArrayList<>();

        for (int left = index, right = nums.length - 1; left < right;) {

            if (left > index && nums[left] == nums[left-1]) {
                left++;
                continue;
            }

            if (nums[left] + nums[right] == sumRequired) {
                twoSumPairs.add(Arrays.asList(nums[left], nums[right]));
                left++;
                right--;
            } else if (nums[left] + nums[right] > sumRequired) {
                right--;
            } else {
                left++;
            }
        }
        return twoSumPairs;
    }
}