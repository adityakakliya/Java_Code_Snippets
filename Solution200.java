class Solution {
    public int numIslands(char[][] grid) {
        int result = 0;

        int rows = grid.length;
        if (rows == 0) {
            return result;
        }
        int cols = grid[0].length;
        boolean[][] isVisited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!isVisited[i][j] && grid[i][j] == '1') {
                    floodfill(grid, i, j, rows, cols, isVisited);
                    result++;
                }
            }
        }
        return result;
    }

    private void floodfill (char[][] grid, int row, int col, int rows, int cols, boolean[][] isVisited) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] != '1' || isVisited[row][col]) {
            return;
        }

        isVisited[row][col] = true;
        floodfill(grid, row + 1, col, rows, cols, isVisited);
        floodfill(grid, row - 1, col, rows, cols, isVisited);
        floodfill(grid, row, col + 1, rows, cols, isVisited);
        floodfill(grid, row, col - 1, rows, cols, isVisited);
    }
}