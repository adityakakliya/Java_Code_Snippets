class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> indexList = new LinkedList<>();

        int[] maxSlidingWindow = new int[nums.length - k + 1];

        for (int left = 0, right = 0; right < nums.length; right++) {
            if (indexList.size() < k) {
                while (!indexList.isEmpty() && nums[indexList.getLast()] < nums[right]) {
                    indexList.removeLast();
                }
                indexList.addLast(right);
            }

            if (right - left + 1 == k) {
                maxSlidingWindow[left] = nums[indexList.getFirst()];
                if (indexList.getFirst() == left) {
                    indexList.removeFirst();
                }
                left++;
            }
        }

        return maxSlidingWindow;
    }
}