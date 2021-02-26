class Solution {
    public int makeConnected(int n, int[][] connections) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int cycles = 0;
        for (int i = 0; i < connections.length; i++) {
            int parent1 = findParent(parent, connections[i][0]);
            int parent2 = findParent(parent, connections[i][1]);

            if (parent1 == parent2) {
                cycles++;
            }
            parent[parent2] = parent1;
        }
        Set<Integer> connectedComputers = new HashSet<>();
        for (int i = 0; i < n; i++) {
            connectedComputers.add(findParent(parent, i));
        }

        if (cycles >= connectedComputers.size() - 1) {
            return connectedComputers.size() - 1;
        }

        return -1;
    }

    private int findParent(int[] parent, int nodeIndex) {
        if (parent[nodeIndex] != nodeIndex) {
            parent[nodeIndex] = findParent(parent, parent[nodeIndex]);
        }
        return parent[nodeIndex];
    }

}