class Trie {
    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode('#');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        root.addString(word, 0);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return root.search(word, 0);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return root.searchPrefix(prefix, 0);
    }
}

class TrieNode {
    char value;
    Map<Character, TrieNode> childNodes;
    TrieNode (char value) {
        this.value = value;
        childNodes = new HashMap<>();
    }

    public void addString (String string, int index) {
        if (index == string.length()) {
            this.childNodes.put('*', null);
        } else {
            childNodes.putIfAbsent(string.charAt(index), new TrieNode(string.charAt(index)));
            childNodes.get(string.charAt(index)).addString(string, index + 1);
        }
    }

    public boolean search (String string, int index) {
        if (index == string.length()) return childNodes.containsKey('*');
        if (childNodes.containsKey(string.charAt(index))) return childNodes.get(string.charAt(index)).search(string, index+1);
        return false;
    }

    public boolean searchPrefix (String string, int index) {
        if (index == string.length()) return true;
        //System.out.println(childNodes.keySet());
        //System.out.println(childNodes.containsKey(string.charAt(index)));
        if (childNodes.containsKey(string.charAt(index))) return childNodes.get(string.charAt(index)).searchPrefix(string, index+1);
        return false;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */