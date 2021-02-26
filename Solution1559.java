class Solution {
    public boolean containsCycle(char[][] grid) {
        int rows = grid.length;
        if (rows == 0) {
            return false;
        }
        int cols = grid[0].length;
        boolean[][] isVisited = new boolean[rows][cols];
        boolean containsCycle = false;
        for (int i = 0; i < rows && !containsCycle; i++) {
            for (int j = 0; j < cols && !containsCycle; j++) {
                if (!isVisited[i][j]) {
                    containsCycle = containsCycle || dfs(i, j, rows, cols, isVisited, grid, -1, -1, grid[i][j]);
                }
            }
        }
        return containsCycle;
    }

    private boolean dfs (int row, int col, int rows, int cols, boolean[][] isVisited, char[][] grid, int parentRow, int parentCol, char parent) {
        if (row == rows || row < 0 || col == cols || col < 0 || grid[row][col] != parent) {
            return false;
        }
        if (isVisited[row][col]) {
            return true;
        }

        isVisited[row][col] = true;
        boolean localContainsCycle = false;
        int[] rowOffset = new int[]{1,-1,0,0};
        int[] colOffset = new int[]{0,0,1,-1};
        for (int i = 0; i < 4 && !localContainsCycle; i++) {
            if (!(row + rowOffset[i] == parentRow && col + colOffset[i] == parentCol)) {
                localContainsCycle = localContainsCycle || dfs (row + rowOffset[i], col + colOffset[i], rows, cols, isVisited, grid, row, col, parent);
            }
        }
        return localContainsCycle;
    }
}