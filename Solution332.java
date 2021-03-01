class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] memo = new int[amount+1][coins.length];
        Arrays.stream(memo).forEach(arr -> Arrays.fill(arr, -1));
        int minCoins = coinChange (coins, amount, 0, memo);
        if (minCoins == Integer.MIN_VALUE) return -1;
        return minCoins;
    }
    
    private int coinChange(int[] coins, int amount, int index, int[][] memo) {
        if (index == coins.length - 1) {
            if (amount % coins[index] == 0) return amount/coins[index];
            return Integer.MIN_VALUE;
        }
        if (memo[amount][index] != -1) return memo[amount][index];
        memo[amount][index] = IntStream.range(0, amount/coins[index] + 1)
            .map((i) -> {
                int remainingCoins = coinChange(coins, amount - i * coins[index], index+1, memo);
                if( remainingCoins == Integer.MIN_VALUE) return Integer.MIN_VALUE;
                return i + remainingCoins;
            })
            .reduce(Integer.MIN_VALUE, (count1, count2) -> {
                if (count1 == Integer.MIN_VALUE) return count2;
                if (count2 == Integer.MIN_VALUE) return count1;
                return Math.min(count1, count2);
            });
        
        return memo[amount][index];
    }
}
