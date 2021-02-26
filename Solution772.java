class Solution {
    public int calculate(String s) {
        Calculator calc = new Calculator(s);
        return (int) calc.calculate();
    }
}

class Calculator {
    String expression;
    Stack<Integer> operandStack;
    Stack<OperatorParenthesis> operatorParenthesisStack;

    Calculator (String expression) {
        this.expression = expression;
        operandStack = new Stack<>();
        operandStack.add(0);
        operatorParenthesisStack = new Stack<>();
    }

    public int calculate () {
        for (int i = 0; i < expression.length(); i++) {
            char currentCharacter = expression.charAt(i);
            if (currentCharacter == ' ') continue;
            OperatorParenthesis operatorParenthesis = getOperatorParenthesis(currentCharacter);
            if (operatorParenthesis == null) {
                int operand = parseOperand(i);
                i += String.valueOf((int)operand).length() - 1;
                operandStack.push(operand);
            } else {
                if (operatorParenthesis == OperatorParenthesis.OPEN_PARENTHESIS) {
                    operatorParenthesisStack.add(operatorParenthesis);
                } else if (operatorParenthesis == OperatorParenthesis.CLOSE_PARENTHESIS) {
                    while (operatorParenthesisStack.peek() != OperatorParenthesis.OPEN_PARENTHESIS) {
                        evaluate();
                    }
                    operatorParenthesisStack.pop();
                } else {
                    int precedence = getPrecedence(operatorParenthesis);
                    while (!operatorParenthesisStack.isEmpty() && precedence <= getPrecedence(operatorParenthesisStack.peek())) {
                        evaluate();
                    }
                    operatorParenthesisStack.add(operatorParenthesis);
                }
            }
        }
        while (!operatorParenthesisStack.isEmpty()) {
            evaluate();
        }
        return operandStack.pop();
    }

    private int parseOperand (int index) {
        int operand = 0;

        while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
            operand  = operand * 10 + (expression.charAt(index) - '0');
            index++;
        }
        return operand;
    }

    private OperatorParenthesis getOperatorParenthesis(char op) {
        if (op == '+') return OperatorParenthesis.ADD;
        if (op == '-') return OperatorParenthesis.SUBSTRACT;
        if (op == '*') return OperatorParenthesis.MULTIPLY;
        if (op == '/') return OperatorParenthesis.DIVIDE;
        if (op == '(') return OperatorParenthesis.OPEN_PARENTHESIS;
        if (op == ')') return OperatorParenthesis.CLOSE_PARENTHESIS;
        return null;
    }

    private int getPrecedence(OperatorParenthesis op) {
        if (op == OperatorParenthesis.ADD || op == OperatorParenthesis.SUBSTRACT) return 0;
        if (op == OperatorParenthesis.MULTIPLY || op == OperatorParenthesis.DIVIDE) return 1;
        return -2;
    }


    private void evaluate() {
        int operand2 = operandStack.pop();
        int operand1 = operandStack.pop();
        int value = applyOperator(operand1, operatorParenthesisStack.pop(), operand2);
        operandStack.push(value);
    }

    private int applyOperator (int operand1, OperatorParenthesis op, int operand2) {
        if (op == OperatorParenthesis.ADD) return operand1 + operand2;
        if (op == OperatorParenthesis.SUBSTRACT) return operand1 - operand2;
        if (op == OperatorParenthesis.MULTIPLY) return operand1 * operand2;
        if (op == OperatorParenthesis.DIVIDE) return operand1 / operand2;
        return 0;
    }

    enum OperatorParenthesis {
        ADD, SUBSTRACT, MULTIPLY, DIVIDE, OPEN_PARENTHESIS, CLOSE_PARENTHESIS;
    };

}