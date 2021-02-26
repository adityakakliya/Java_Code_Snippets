class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = 1;
        int redundantConnection = -1;
        for (int i = 0; i < edges.length; i++) {
            n = Math.max(edges[i][1], n);
        }

        int[] parent = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            int parent1 = findParent(parent, edges[i][0]);
            int parent2 = findParent(parent, edges[i][1]);

            if (parent1 == parent2) {
                redundantConnection = i;
            } else if (parent1 > parent2) {
                parent[edges[i][0]] = parent2;
                parent[parent1] = parent2;
            } else {
                parent[edges[i][1]] = parent1;
                parent[parent2] = parent1;
            }
        }

        return edges[redundantConnection];
    }

    private int findParent(int[] parent, int nodeIndex) {
        if (parent[nodeIndex] != nodeIndex) {
            parent[nodeIndex] = findParent(parent, parent[nodeIndex]);
        }
        return parent[nodeIndex];
    }
}