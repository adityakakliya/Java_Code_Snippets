class Solution212 {
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> visited = new HashSet<>();
        int rows = board.length;
        int cols = board[0].length;
        Trie trie = new Trie(new TrieNode('$', null));
        Arrays.stream(words).forEach(word -> trie.root.add(word, 0));
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                search(board, row, col, rows, cols, trie.root, visited);
            }
        }
        return new ArrayList<>(visited);
    }

    private void search (char[][] board, int row, int col, int rows, int cols, TrieNode node, Set<String> visited) {
        if (node.str != null) {
            visited.add(node.str);
            node.str = null;
        };
        if (row < 0 || row == rows || col < 0 || col == cols || !node.childNodes.containsKey(board[row][col])) return;
        char c = board[row][col];
        board[row][col] = '-';
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            search (board, newRow, newCol, rows, cols, node.childNodes.get(c), visited);
        }
        board[row][col] = c;
    }
}


class Trie {
    TrieNode root;

    Trie (TrieNode root) {
        this.root = root;
    }
}

class TrieNode {
    char value;
    Map<Character, TrieNode> childNodes;
    String str;

    TrieNode (char value, String str) {
        this.value = value;
        childNodes = new HashMap<>();
        this.str = str;
    }

    public void add (String string, int index) {
        if (index == string.length()) this.str = string;
        else {
            childNodes.computeIfAbsent(string.charAt(index), node -> new TrieNode(string.charAt(index), null)).add(string, index+1);
        }
    }
}