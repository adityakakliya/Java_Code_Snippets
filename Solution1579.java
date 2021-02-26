class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] parentAlice = new int[n];
        int[] parentBob = new int[n];
        boolean needEdge = false;
        int maxNumEdgesToRemove = 0;
        for (int i = 0; i < n; i++) {
            parentAlice[i] = parentBob[i] = i;
        }

        for (int[] edge : edges) {
            needEdge = false;
            if (edge[0] == 3) {
                int parent1 = findParent(edge[1] - 1, parentAlice);
                int parent2 = findParent(edge[2] - 1, parentAlice);
                if (parent1 != parent2) {
                    needEdge = true;
                    parentAlice[parent2] = parent1;
                }
                parent1 = findParent(edge[1] - 1, parentBob);
                parent2 = findParent(edge[2] - 1, parentBob);
                if (parent1 != parent2) {
                    needEdge = true;
                    parentBob[parent2] = parent1;
                }
                if (!needEdge) {
                    maxNumEdgesToRemove++;
                }
            }
        }


        for (int[] edge : edges) {
            needEdge = false;
            if (edge[0] == 1) {
                int parent1 = findParent(edge[1] - 1, parentAlice);
                int parent2 = findParent(edge[2] - 1, parentAlice);
                if (parent1 != parent2) {
                    needEdge = true;
                    parentAlice[parent2] = parent1;
                } else {
                    maxNumEdgesToRemove++;
                }
            } else if (edge[0] == 2) {
                int parent1 = findParent(edge[1] - 1, parentBob);
                int parent2 = findParent(edge[2] - 1, parentBob);
                if (parent1 != parent2) {
                    needEdge = true;
                    parentBob[parent2] = parent1;
                } else {
                    maxNumEdgesToRemove++;
                }
            }
        }

        int globalParentAlice = findParent(0, parentAlice);
        int globalParentBob = findParent(0, parentBob);
        for (int i = 1; i < n; i++) {
            if (findParent(i, parentAlice) != globalParentAlice || findParent(i, parentBob) != globalParentBob) {
                return -1;
            }
        }
        return maxNumEdgesToRemove;
    }

    private int findParent(int node, int[] parent) {
        if (parent[node] != node) {
            parent[node] = findParent(parent[node], parent);
        }
        return parent[node];
    }
}