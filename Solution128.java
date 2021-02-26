class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> childParent = new HashMap<>();
        Map<Integer, Integer> parentFreq = new HashMap<>();
        Set<Integer> numsSet = new HashSet<>(Arrays.asList(Arrays.stream(nums).boxed().toArray( Integer[]::new )));
        int longestConsecutive = 0;
        for (int num : numsSet) {
            int parent = findParent(childParent, num);
            for (int j = -1; j <= 1; j+=2) {
                if (childParent.containsKey(parent + j)) {
                    childParent.put(findParent(childParent, parent + j), parent);
                }
            }
        }

        for (int num : numsSet) {
            int parent = findParent(childParent, num);
            parentFreq.put(parent, parentFreq.getOrDefault(parent, 0) + 1);
            longestConsecutive = Math.max(longestConsecutive, parentFreq.get(parent));
        }
        return longestConsecutive;
    }

    private int findParent(Map<Integer, Integer> childParent, int child) {
        if (!childParent.containsKey(child)) {
            childParent.put(child, child);
            return child;
        }
        if (childParent.get(child) == child) {
            return child;
        }

        int oldParent = childParent.get(child);
        int parent = findParent(childParent, oldParent);
        childParent.put(child, parent);
        return parent;
    }
}