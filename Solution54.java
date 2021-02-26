/*

1 2 3 4
5 6 7 8
9 1 2 3

*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiralOrder = new ArrayList<>();
        int top = 0;
        int left = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;
        int dir = 0;
        while (top <= bottom && left <= right) {
            if (dir == 0) {
                for (int i = left; i <= right; i++) {
                    spiralOrder.add(matrix[top][i]);
                }
                top++;
            } else if (dir == 1) {
                for (int i = top; i <= bottom; i++) {
                    spiralOrder.add(matrix[i][right]);
                }
                right--;
            } else if (dir == 2) {
                for (int i = right; i >= left; i--) {
                    spiralOrder.add(matrix[bottom][i]);
                }
                bottom--;
            } else {
                for (int i = bottom; i>= top; i--) {
                    spiralOrder.add(matrix[i][left]);
                }
                left++;
            }
            dir++;
            dir = dir%4;
        }
        return spiralOrder;
    }
}