class Solution {
    public int earliestAcq(int[][] logs, int N) {
        int[] parent = new int[N];
        int earliestAcq = logs[0][0];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        Arrays.sort(logs, (a,b) -> a[0] - b[0]);
        for (int i = 0; i < logs.length; i++) {
            int parent1 = findParent(logs[i][1], parent);
            int parent2 = findParent(logs[i][2], parent);
            parent[parent2] = parent1;
            if (parent2 != parent1) {
                earliestAcq = logs[i][0];
            }
        }

        int globalParent = findParent(parent[0], parent);
        for (int i = 1; i < N; i++) {
            if (findParent(parent[i], parent) != globalParent) {
                return -1;
            }
        }
        return earliestAcq;
    }

    private int findParent(int id, int[] parent) {
        if (parent[id] != id) {
            parent[id] = findParent(parent[id], parent);
        }
        return parent[id];
    }
}