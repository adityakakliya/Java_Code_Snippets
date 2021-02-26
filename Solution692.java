class Solution692 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordCount = new HashMap<>();
        TreeMap<Integer, TreeSet<String>> wordsCount = new TreeMap<>((a,b) -> b - a);
        List<String> topKFrequent = new ArrayList<>();
        for (String word : words) {
            int currentCount = wordCount.getOrDefault(word, 0);
            if (wordCount.containsKey(word)) {
                wordsCount.get(currentCount).remove(word);
            }
            wordCount.put(word, currentCount + 1);
            wordsCount.computeIfAbsent(currentCount + 1, set -> new TreeSet<>()).add(word);
        }
        Iterator<Map.Entry<Integer, TreeSet<String>>> iterator = wordsCount.entrySet().iterator();
        while (k > 0) {
            Map.Entry<Integer, TreeSet<String>> entry = iterator.next();
            int repeat = Math.min (entry.getValue().size(), k);
            Iterator<String> setIterator = entry.getValue().iterator();
            while (repeat -- > 0) {
                topKFrequent.add(setIterator.next());
                k--;
            }
        }
        return topKFrequent;

    }
}