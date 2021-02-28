class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        IntStream.range(0, rows).forEach((row) -> {
            if (row == 0 || row == rows - 1) {
                IntStream.range(0, cols).forEach((col) -> {
                    doDFS(grid, rows, cols, row, col);
                });
            } else {
                if (grid[row][0] == 0) doDFS(grid, rows, cols, row, 0);
                if (grid[row][cols-1] == 0) doDFS(grid, rows, cols, row, cols-1);
            }
        });
    
        int closedIslands = 0;
        for (int i = 1; i < rows-1; i++) {
            for(int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0) {
                    closedIslands++;
                    doDFS(grid, rows, cols, i, j);
                }
            }
        }
        return closedIslands;
    }
    
    private void doDFS (int[][] grid, int rows, int cols, int row, int col) {
        if (row < 0 || row == rows || col < 0 || col == cols || grid[row][col] != 0) return;
        grid[row][col] = 1;
        int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
        IntStream.range(0, dirs.length).forEach(i -> doDFS(grid, rows, cols, row + dirs[i][0], col + dirs[i][1]));
    }
}
