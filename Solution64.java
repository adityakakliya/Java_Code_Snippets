class Solution {
    public int minPathSum(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int pathSumTillNow = Integer.MAX_VALUE;
                if (i-1 >= 0) {
                    pathSumTillNow = Math.min(pathSumTillNow, matrix[i-1][j]);
                }
                if (j-1 >= 0) {
                    pathSumTillNow = Math.min(pathSumTillNow, matrix[i][j-1]);
                }
                matrix[i][j] += pathSumTillNow == Integer.MAX_VALUE ? 0 : pathSumTillNow;
            }
        }
        return matrix[r-1][c-1];
    }
}