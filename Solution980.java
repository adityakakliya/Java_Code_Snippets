class Solution {
    public int uniquePathsIII(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int startIndexRow = -1;
        int startIndexCol = -1;
        int emptySquares = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    startIndexRow = i;
                    startIndexCol = j;
                } else if (grid[i][j] == 0) {
                    emptySquares++;
                }
            }
        }

        return uniquePathsIII (grid, startIndexRow, startIndexCol, rows, cols, emptySquares);
    }

    private int uniquePathsIII (int[][] grid, int startIndexRow, int startIndexCol, int rows, int cols, int emptySquares) {

        if (startIndexRow < 0 || startIndexRow == rows || startIndexCol < 0 || startIndexCol == cols || grid[startIndexRow][startIndexCol] == -1 || grid[startIndexRow][startIndexCol] == 3) {
            return 0;
        }

        if (grid[startIndexRow][startIndexCol] == 2) {
            return emptySquares == 0 ? 1 : 0;
        }

        int gridValue = grid[startIndexRow][startIndexCol];
        grid[startIndexRow][startIndexCol] = 3;
        int uniquePathsIII = 0;
        int[] rowOffset = new int[]{1,-1,0,0};
        int[] colOffset = new int[]{0,0,1,-1};

        for (int j = 0; j < 4; j++) {
            uniquePathsIII += uniquePathsIII(grid, startIndexRow + rowOffset[j], startIndexCol + colOffset[j], rows, cols, emptySquares - (gridValue == 0 ? 1 : 0));
        }
        grid[startIndexRow][startIndexCol] = gridValue;

        return uniquePathsIII;
    }
}