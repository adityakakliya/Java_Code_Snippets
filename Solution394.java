/*

before [ comes a number
] -> before this and till [ -> always a string


*/


class Solution {
    public String decodeString(String s) {
        Stack<String> stack = new Stack();
        StringBuilder decodeString = new StringBuilder();

        for (Character c : s.toCharArray()) {
            if (Character.toString(c).equals("]")) {
                StringBuilder localString = new StringBuilder();
                while (!stack.peek().equals("[")) {
                    localString.insert(0, stack.pop());
                }
                stack.pop();
                StringBuilder repeat = new StringBuilder();
                while (!stack.isEmpty() && !stack.peek().equals("]") && Character.isDigit(stack.peek().charAt(0))) {
                    repeat.insert(0, stack.pop());
                }
                stack.add(localString.toString().repeat(Integer.parseInt(repeat.toString())));
            } else {
                stack.add(Character.toString(c));
            }
        }

        for (String localDecodeString: stack) {
            decodeString.append(localDecodeString);
        }
        return decodeString.toString();

    }
}