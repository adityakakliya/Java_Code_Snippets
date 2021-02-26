class Solution {
    public int findCircleNum(int[][] M) {
        int[] groupNumber = new int[M.length];
        BitSet bitset = new BitSet(M.length);
        for (int i = 0; i < groupNumber.length; i++) {
            groupNumber[i] = i;
        }

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1) {
                    int group1 = findGroup(i, groupNumber);
                    int group2 = findGroup(j, groupNumber);
                    groupNumber[j] = group1;
                    groupNumber[group2] = group1;
                }
            }
        }

        for (int i = 0; i < groupNumber.length; i++) {
            bitset.set(findGroup(groupNumber[i], groupNumber));
        }

        return bitset.cardinality();
    }

    private int findGroup(int group, int[] groupNumber) {
        if (groupNumber[group] != group) {
            groupNumber[group] = findGroup(groupNumber[group], groupNumber);
        }
        return groupNumber[group];
    }
}