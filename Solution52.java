class Solution {
    public int totalNQueens(int n) {
        int[] columns = new int[n];
        return totalNQueens(columns, 0);
    }
    
    private int totalNQueens(int[] columns, int row) {
        if (row == columns.length) return 1;
        int ans = 0;
        for (int i = 0; i < columns.length; i++) {
            if (canPlaceQueen(columns, row, i)) {
                columns[row] = i;
                ans += totalNQueens (columns, row+1);
            } 
        }
        return ans;
    }
    private boolean canPlaceQueen(int[] columns, int row, int col) {
        return !IntStream.range(0, row)
                .anyMatch(i -> illegalPlacement(i, columns[i], row, col));
    }
    
    private boolean illegalPlacement (int row1, int col1, int row2, int col2) {
        if (col1 == col2) return true;
        if (Math.abs(row1 - row2) == Math.abs(col1 - col2)) return true;
        return false;
    }
}
