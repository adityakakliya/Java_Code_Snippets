class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (int[] slot1, int[] slot2) -> Integer.compare(slot1[0], slot2[0]));
        Arrays.sort(slots2, (int[] slot1, int[] slot2) -> Integer.compare(slot1[0], slot2[0]));
        
        int index1 = 0;
        int index2 = 0;
        int length1 = slots1.length;
        int length2 = slots2.length;
        while (index1 < length1 && index2 < length2) {
            if (isIntersectionPresent(slots1, index1, slots2, index2)) {
                int startTime = Math.max(slots1[index1][0], slots2[index2][0]);
                int endTime = Math.min(slots1[index1][1], slots2[index2][1]);
                if (endTime - startTime >= duration) return List.of(startTime, startTime + duration);
            }
            if (slots1[index1][1] >= slots2[index2][1]) {
                index2++;
            } else {
                index1++;
            }
        }
        return new ArrayList<>();
    }
    
    private boolean isIntersectionPresent(int[][] slots1, int index1, int[][] slots2, int index2) {
        return (slots2[index2][0] <= slots1[index1][1] && slots2[index2][1] >= slots1[index1][1]) ||
            (slots1[index1][0] <= slots2[index2][1] && slots1[index1][1] >= slots2[index2][1]);
    }
}
