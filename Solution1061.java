class Solution {
    public String smallestEquivalentString(String A, String B, String S) {
        Map<Character, Character> characterParent = new HashMap<>();
        Map<Character, Character> parentMinChild = new HashMap<>();
        StringBuilder smallestEquivalentString = new StringBuilder();
        for (int i = 0; i < A.length(); i++) {
            characterParent.put(findParent(A.charAt(i), characterParent), findParent(B.charAt(i), characterParent));
        }

        for (Character c : characterParent.keySet()) {
            Character globalParent = findParent(characterParent.get(c), characterParent);
            parentMinChild.putIfAbsent(globalParent, globalParent);
            if (Character.compare(c, parentMinChild.get(globalParent)) < 0) {
                parentMinChild.put(globalParent, c);
            }
        }

        for (Character c : S.toCharArray()) {
            smallestEquivalentString.append(characterParent.containsKey(c) ? parentMinChild.get(findParent(c, characterParent)) : c);
        }
        return smallestEquivalentString.toString();
    }

    private Character findParent (Character c, Map<Character, Character> characterParent) {
        if (!characterParent.containsKey(c) || characterParent.get(c) == c) {
            characterParent.putIfAbsent(c,c);
        } else {
            characterParent.put(c, findParent(characterParent.get(c), characterParent));
        }
        return characterParent.get(c);
    }
}