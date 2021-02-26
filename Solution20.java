class Solution {
    public boolean isValid(String str) {
        Stack<Character> s = new Stack<>();
        for (Character c : str.toCharArray()) {
            switch (c) {
                case '(' :
                    s.push(')');
                    break;
                case '[' :
                    s.push(']');
                    break;
                case '{' :
                    s.push('}');
                    break;
                default :
                    if (s.size() == 0 || s.peek() != c) {
                        return false;
                    }
                    s.pop();
            }
        }
        return s.isEmpty();
    }
}