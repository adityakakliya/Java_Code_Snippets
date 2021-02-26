class Solution {
    public int ladderLength(String word, String target, List<String> list) {
        Set<String> validWords = new HashSet<>(list);
        Queue<String> queue = new LinkedList<>();
        queue.add(word);
        int level = 0;
        Set<String> visitedWords = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            visitedWords.addAll(queue);
            List<String> nextWords = new ArrayList<>();
            level++;
            while (size -- > 0) {
                String currentWord = queue.poll();
                if (currentWord.equals(target)) {
                    return level;
                }
                findNextWords(validWords, visitedWords, nextWords, currentWord);
            }
            //System.out.println(nextWords);
            queue.addAll(nextWords);
        }
        return 0;
    }

    private void findNextWords(Set<String> validWords, Set<String> visitedWords, List<String> nextWords, String currentWord) {
        StringBuilder newWord = new StringBuilder(currentWord);
        for (int i = 0; i < newWord.length(); i++) {
            Character originalCharacter = newWord.charAt(i);
            for (Character c = 'a'; c <= 'z'; c++) {
                newWord.setCharAt(i, c);
                String word = newWord.toString();
                if (validWords.contains(word) && !visitedWords.contains(word)) {
                    nextWords.add(word);
                }
            }
            newWord.setCharAt(i, originalCharacter);
        }
    }
}