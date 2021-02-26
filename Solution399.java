class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, String> childParent = new HashMap<>();
        Map<String, Double> childFactor = new HashMap<>();
        double[] calcEquation = new double[queries.size()];

        for (int i = 0; i < equations.size(); i++) {
            String parent1 = updateChildParent (equations.get(i).get(0), childParent, childFactor);
            String parent2 = updateChildParent (equations.get(i).get(1), childParent, childFactor);
            childFactor.put(parent1, values[i] * childFactor.get(equations.get(i).get(1)) / childFactor.get(equations.get(i).get(0)));
            childParent.put(parent1, parent2);
        }

        for (int i = 0; i < queries.size(); i++) {
            if (childParent.containsKey(queries.get(i).get(0)) && childParent.containsKey(queries.get(i).get(1)) &&
                    updateChildParent (queries.get(i).get(0), childParent, childFactor).equals(updateChildParent (queries.get(i).get(1), childParent, childFactor))) {
                calcEquation[i] = childFactor.get(queries.get(i).get(0)) / childFactor.get(queries.get(i).get(1));
            } else {
                calcEquation[i] = -1.0;
            }
        }

        return calcEquation;
    }


    private String updateChildParent (String child, Map<String, String> childParent, Map<String, Double> childFactor) {
        if (!childParent.containsKey(child)) {
            childParent.put(child, child);
            childFactor.put(child, 1.0);
            return child;
        }

        String parent = childParent.get(child);
        if (parent.equals(child)) {
            return child;
        }
        String grandParent = updateChildParent(parent, childParent, childFactor);
        childParent.put(child, grandParent);
        childFactor.put(child, childFactor.get(child) * childFactor.get(parent));
        return grandParent;
    }


    /*

    a/b = x1
    a = b * x1

    b -> b
    a - > x1

    c/b = x2

    a/d = x4
    d/a = x4



    a = b * x1
    b = c * x2

    a-> b -> c -> e


    d -> a * factor






    */
}