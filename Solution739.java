class Solution {
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] dailyTemperatures = new int[T.length];

        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                dailyTemperatures[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return dailyTemperatures;
    }
}