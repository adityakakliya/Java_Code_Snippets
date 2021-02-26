class Solution {
    public int minimumCost(int N, int[][] connections) {
        List<Pair<Integer, Integer>> costIndex = new ArrayList<>();
        for (int i = 0; i < connections.length; i++) {
            costIndex.add(new Pair<>(connections[i][2], i));
        }
        int n = N;
        Collections.sort(costIndex, new MinCostToSupplyWaterComparator());
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int minCostToSupplyWater = 0;
        for (int j = 0; j < costIndex.size(); j++) {
            int i = costIndex.get(j).getValue();
            connections[i][0]--;
            connections[i][1]--;
            int parent1 = findParent(parent, connections[i][0]);
            int parent2 = findParent(parent, connections[i][1]);
            if (parent1 != parent2) {
                parent[parent2] = parent1;
                minCostToSupplyWater += connections[i][2];
            }
        }
        int globalParent = findParent(parent, 0);
        for (int i = 1; i < n; i++) {
            if (globalParent != findParent(parent, i)) {
                return -1;
            }
        }

        return minCostToSupplyWater;
    }

    private int findParent(int[] parent, int nodeIndex) {
        if (parent[nodeIndex] != nodeIndex) {
            parent[nodeIndex] = findParent(parent, parent[nodeIndex]);
        }
        return parent[nodeIndex];
    }
}

class MinCostToSupplyWaterComparator implements Comparator<Pair<Integer, Integer>> {
    @Override
    public int compare (Pair<Integer, Integer> pair1, Pair<Integer, Integer> pair2) {
        return Integer.compare(pair1.getKey(), pair2.getKey());
    }
}