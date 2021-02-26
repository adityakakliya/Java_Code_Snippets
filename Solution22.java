class Solution {
    public List<String> generateParenthesis(int n) {

        List<String> list = new ArrayList<>();
        generate(list, n, n, new StringBuilder());
        return list;
    }

    private void generate (List<String> list, int open, int close, StringBuilder s) {
        if (open < 0 || close < 0) {
            return;
        }
        if (open > close) {
            return;
        }
        if (open == 0 && close == 0) {
            list.add(s.toString());
            return;
        }

        generate(list, open - 1, close, s.append('('));
        s.deleteCharAt(s.length() - 1);
        generate(list, open, close - 1, s.append(')'));
        s.deleteCharAt(s.length() - 1);
    }
}