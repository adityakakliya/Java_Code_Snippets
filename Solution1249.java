class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder minRemoveToMakeValid = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(minRemoveToMakeValid.length());
                minRemoveToMakeValid.append('(');
            } else if (s.charAt(i) == ')') {
                if (stack.size() > 0) {
                    stack.pop();
                    minRemoveToMakeValid.append(')');
                }
            } else {
                minRemoveToMakeValid.append(s.charAt(i));
            }
        }
        int deletions = 0;
        for (Integer i: stack) {
            minRemoveToMakeValid.deleteCharAt(i - deletions);
            deletions++;
        }
        return minRemoveToMakeValid.toString();
    }
}