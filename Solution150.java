class Solution {
    public int evalRPN(String[] tokens) {
        int eval = 0;
        Stack<Integer> operands = new Stack();

        for (String token : tokens) {
            if (isOperator(token)) {
                evaluate(token, operands);
            } else {
                operands.add(Integer.parseInt(token));
            }
        }
        return operands.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+")
                || token.equals("-")
                || token.equals("/")
                || token.equals("*");
    }
    private void evaluate(String token, Stack<Integer> stack) {
        int operand2 = stack.pop();
        int operand1 = stack.pop();

        if (token.equals("+")) {
            stack.add(operand1 + operand2);
        } else if (token.equals("-")) {
            stack.add(operand1 - operand2);
        } else if (token.equals("*")) {
            stack.add(operand1 * operand2);
        } else {
            stack.add(operand1/operand2);
        }
    }
}