class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> map = new HashMap<>();
        Map<Integer, TreeSet<String>> mergedAccounts = new HashMap<>();
        List<List<String>> accountsMerge = new ArrayList<>();
        int[] parent = new int[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            parent[i] = i;
        }

        int groupCounter = 0;
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                if(map.containsKey(account.get(i))) {
                    int p = findParent(map.get(account.get(i)), parent);
                    parent[parent[groupCounter]] = p;
                    parent[groupCounter] = p;
                } else {
                    map.put(account.get(i), groupCounter);
                }
            }
            groupCounter++;
        }


        for (Map.Entry <String, Integer> entry : map.entrySet()) {
            mergedAccounts.putIfAbsent(findParent(entry.getValue(), parent), new TreeSet<>());
            mergedAccounts.get(findParent(entry.getValue(), parent)).add(entry.getKey());
        }

        for (Map.Entry<Integer, TreeSet<String>> entry : mergedAccounts.entrySet()) {
            List<String> list = new ArrayList<>();
            list.add(accounts.get(entry.getKey()).get(0));
            list.addAll(entry.getValue());
            accountsMerge.add(list);
        }
        return accountsMerge;
    }

    private int findParent(int groupNumber, int[] parent) {
        if (parent[groupNumber] != groupNumber) {
            parent[groupNumber] = findParent(parent[groupNumber], parent);
        }
        return parent[groupNumber];
    }
}