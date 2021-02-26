class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[1] - b[1]);

        int minMeetingRooms = 0;

        for (int[] interval : intervals) {
            if (!queue.isEmpty() && queue.peek()[1] <= interval[0]) {
                queue.remove();
            }
            queue.add(interval);
            minMeetingRooms = Math.max(minMeetingRooms, queue.size());
        }

        return minMeetingRooms;
    }
}