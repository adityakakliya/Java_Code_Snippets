class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        Map<String, String> childParent = new HashMap<>();

        for (List<String> pair : pairs) {
            String parent1 = findParent(pair.get(0), childParent);
            String parent2 = findParent(pair.get(1), childParent);
            childParent.put(parent1, parent2);
        }
        if (words1.length != words2.length) {
            return false;
        }
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].compareTo(words2[i]) != 0 && findParent(words1[i], childParent).compareTo(findParent(words2[i], childParent)) != 0) {
                return false;
            }
        }

        return true;
    }

    private String findParent(String word, Map<String, String> childParent) {
        if (!childParent.containsKey(word)) {
            childParent.put(word, word);
            return word;
        }
        String oldParent = childParent.get(word);

        if (oldParent.compareTo(childParent.get(oldParent)) == 0) {
            return oldParent;
        }

        String parent = findParent(oldParent, childParent);
        childParent.put(word, parent);
        return parent;
    }
}