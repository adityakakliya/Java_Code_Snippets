class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Pair<Character, Integer>> stack = new Stack<>();
        StringBuilder removeDuplicates = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (stack.isEmpty() || stack.peek().getKey() != c) {
                stack.push(new Pair<>(c, 1));
            } else if (stack.peek().getValue() == k-1 && stack.peek().getKey() == c) {
                while (!stack.isEmpty() && stack.peek().getKey() == c) {
                    stack.pop();
                }
            } else if (stack.peek().getKey() == c) {
                stack.push(new Pair<>(c, stack.peek().getValue() + 1));
            }
        }

        for (Pair<Character, Integer> pair : stack) {
            removeDuplicates.append(pair.getKey());
        }

        return removeDuplicates.toString();
    }
}