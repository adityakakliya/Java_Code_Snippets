class Solution {
    private Count count = new Count();
    private Map<Character, Integer> charToCount = new HashMap<>();
    public int numTilePossibilities(String tiles) {
        for (char c : tiles.toCharArray()) {
            charToCount.put(c, charToCount.getOrDefault(c, 0) + 1);
        }
        countPermutations();
        return count.count;
    }
    
    private class Count {
        int count = 0;
    }
    
    private void countPermutations() {
        charToCount.keySet()
                .stream()
                .filter(key -> charToCount.get(key) > 0)
                .forEach(key -> {
                    count.count++;
                    int currentCount = charToCount.get(key);
                    charToCount.put(key, currentCount-1);
                    countPermutations();
                    charToCount.put(key, currentCount);
                });
    }
}
