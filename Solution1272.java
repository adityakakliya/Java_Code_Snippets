class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> removeInterval = new ArrayList<>();
        for (int[] interval : intervals) {
            if (toBeRemoved[0] >= interval[1] || interval[0] >= toBeRemoved[1]) {
                removeInterval.add(Arrays.asList(interval[0], interval[1]));
            } else {
                if (toBeRemoved[0] > interval[0]) {
                    removeInterval.add(Arrays.asList(interval[0], toBeRemoved[0]));
                }
                if (toBeRemoved[1] < interval[1]) {
                    removeInterval.add(Arrays.asList(toBeRemoved[1], interval[1]));
                }
            }
        }
        return removeInterval;
    }
}