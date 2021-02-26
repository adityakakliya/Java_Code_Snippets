class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);
        int minPointer = 0;
        int maxPointer = A.length -1;
        int twoSumLessThanK = -1;
        for (;minPointer < maxPointer;) {
            if (A[minPointer] + A[maxPointer] >= K) {
                maxPointer--;
            } else {
                twoSumLessThanK = Math.max(twoSumLessThanK, A[minPointer] + A[maxPointer]);
                minPointer++;
            }
        }
        return twoSumLessThanK;
    }
}