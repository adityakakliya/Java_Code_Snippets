class Solution {
    public boolean equationsPossible(String[] equations) {
        Map<String, String> childParent = new HashMap<>();
        String globalParent = null;
        for (String equation : equations) {
            String parent1 = findParent(childParent, equation.substring(0,1));
            String parent2 = findParent(childParent, equation.substring(3,4));
            if (equation.charAt(1) == '=') {
                childParent.put(parent1, parent2);
            }
        }

        for (String equation : equations) {
            if ((equation.charAt(1) == '=' && findParent(childParent, equation.substring(0, 1)).compareTo(findParent(childParent, equation.substring(3,4))) != 0) || (equation.charAt(1) == '!' && findParent(childParent, equation.substring(0, 1)).compareTo(findParent(childParent, equation.substring(3,4))) == 0)) {
                return false;
            }
        }
        return true;
    }

    private String findParent(Map<String, String> childParent, String variable) {
        if (!childParent.containsKey(variable) || childParent.get(variable).compareTo(variable) == 0) {
            childParent.putIfAbsent(variable, variable);
        } else {
            childParent.put(variable, findParent(childParent, childParent.get(variable)));
        }
        return childParent.get(variable);
    }
}