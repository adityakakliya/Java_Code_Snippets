class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        int rows = A.length;
        int cols = A[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols/2; col++) {
                int leftPixel = A[row][col];
                int rightPixel = A[row][cols-1-col];
                leftPixel = leftPixel == 1 ? 0 : 1;
                rightPixel = rightPixel == 1 ? 0 : 1;
                A[row][col] = rightPixel;
                A[row][cols-1-col] = leftPixel;
            }
            if (cols%2 == 1) {
                A[row][cols/2] = A[row][cols/2] == 1 ? 0 : 1;
            }
        }
        return A;
    }
}