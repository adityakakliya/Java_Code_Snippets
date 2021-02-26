class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] updateMatrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 1) {
                    int nearestZero = doBFS(row, col, matrix, rows, cols);
                    updateMatrix[row][col] = nearestZero;
                }
            }
        }
        return updateMatrix;
    }

    private int doBFS(int row, int col, int[][] matrix, int rows, int cols) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(getOneDIndex(row, col, cols));
        visited.add(getOneDIndex(row, col, cols));
        int nearestZero = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int currentOneDIndex = queue.remove();
                int[] currentTwoDIndex = getTwoDIndex(currentOneDIndex, cols);
                if (matrix[currentTwoDIndex[0]][currentTwoDIndex[1]] == 0) {
                    return nearestZero;
                }
                addValidNeighbours (currentOneDIndex, visited, queue, rows, cols);
            }
            nearestZero++;
        }
        return Integer.MAX_VALUE;
    }

    private void addValidNeighbours (int index, Set<Integer> visited, Queue<Integer> queue, int rows, int cols) {
        int[][] dirs = {{-1,0},{0,1},{0,-1},{1,0}};
        int[] twoDIndex = getTwoDIndex(index, cols);

        for (int[] dir : dirs) {
            int newRow = twoDIndex[0] + dir[0];
            int newCol = twoDIndex[1] + dir[1];
            if (isValid(newRow, newCol, rows, cols)) {
                int oneDIndex = getOneDIndex(newRow, newCol, cols);
                if (!visited.contains(oneDIndex)) {
                    visited.add(oneDIndex);
                    queue.add(oneDIndex);
                }
            }
        }
    }

    private boolean isValid(int row, int col, int rows, int cols) {
        if (row < 0 || row == rows || col < 0 || col == cols) {
            return false;
        }
        return true;
    }
    private int[] getTwoDIndex(int index, int cols) {
        return new int[]{index / cols, index % cols};
    }
    private int getOneDIndex(int row, int col, int cols) {
        return row * cols + col;
    }
}
