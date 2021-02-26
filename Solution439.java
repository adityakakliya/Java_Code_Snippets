class Solution {
    public String parseTernary(String expression) {
        if (expression.length() == 1) {
            return expression;
        }
        int level = 1;
        int startIndex = 2;
        while (level > 0) {
            if (expression.charAt(startIndex) == ':') {
                level--;
            } else if (expression.charAt(startIndex) == '?') {
                level++;
            }
            startIndex++;
        }
        if (expression.charAt(0) == 'T') {
            return parseTernary(expression.substring(2, startIndex-1));
        }
        return parseTernary(expression.substring(startIndex));
    }
}