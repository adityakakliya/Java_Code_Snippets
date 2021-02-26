class Solution {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        int k = costs[0].length;
        int[][] memo = new int[n][k];
        Arrays.stream(memo).forEach(i -> Arrays.fill(i, -1));
        return minCost(costs, -1, 0, memo, k);

    }

    private int minCost(int[][] costs, int previousColorIndex, int currentIndex, int[][] memo, int k) {
        if (currentIndex == costs.length) return 0;
        if (previousColorIndex != -1 && memo[currentIndex][previousColorIndex] != -1) return memo[currentIndex][previousColorIndex];

        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            if (i != previousColorIndex) {
                minCost = Math.min(minCost, costs[currentIndex][i] + minCost(costs, i, currentIndex + 1, memo, k));
            }
        }
        if (previousColorIndex != -1) memo[currentIndex][previousColorIndex] = minCost;
        return minCost;
    }
}