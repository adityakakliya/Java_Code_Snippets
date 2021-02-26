/*
. -> current
.. -> remove
/ -> skip
. -> skip

split -> /

*/


class Solution {
    public String simplifyPath(String pth) {
        StringBuilder canonicalPath = new StringBuilder();
        Stack<String> directories = new Stack();
        String[] path = pth.split("/");

        for (String p : path) {
            if (p.equals("..")) {
                if (!directories.isEmpty()) {
                    directories.pop();
                }
            } else if (!p.equals("/") && !p.equals(".") && !p.equals("")) {
                directories.add(p);

            }
        }

        directories.forEach((p) -> {
            canonicalPath.append("/").append(p);
        });

        return canonicalPath.length() == 0 ? "/" : canonicalPath.toString();
    }
}