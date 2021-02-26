/*



 */


class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        int[] positions = new int[n];

        solve(list, positions, 0, n);
        return list;
    }

    private static void solve(List<List<String>> list, int[] positions, int index, int n) {
        if (index == n) {
            // add to list
            List<String> validPositions = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder s = new StringBuilder(".".repeat(n));
                s.setCharAt(positions[i], 'Q');
                validPositions.add(s.toString());
            }
            list.add(validPositions);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (canAddQueen(positions, i, index)) {
                positions[index] = i;
                solve(list, positions, index + 1, n);
            }
        }
    }

    private static boolean canAddQueen(int[] positions, int col, int row) {
        for (int i = 0; i < row; i++) {
            if (positions[i] == col || row - i == Math.abs(col - positions[i])) {
                return false;
            }
        }
        return true;
    }
}