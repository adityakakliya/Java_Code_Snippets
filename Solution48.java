class Solution {
    int[][] matrix;
    public void rotate(int[][] matrix) {
        this.matrix = matrix;
        int n = matrix.length - 1;
        int low = 0;

        while (low <= n) {
            int len = n - low;
            for (int i = 0; i < len; i++) {
                swap(low, low + i, low+i, n);
                swap(low, low + i, n, n-i);
                swap(low, low + i, n-i, low);
            }
            low++;
            n--;
        }
    }
    private void swap(int r1,int c1, int r2, int c2) {
        int saved = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = saved;
    }
}