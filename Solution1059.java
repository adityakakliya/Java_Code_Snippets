class Solution {
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        Graph graph = new Graph();
        Arrays.stream(edges).forEach(graph::addEdge);
        return graph.leadsToDestination(source, destination);
    }
}

class Graph {
    Map<Integer, List<Integer>> adjacencyList;
    Set<Integer> visited;
    Set<Integer> visiting;
    boolean containsSelfLoop;
    Graph() {
        adjacencyList = new HashMap<>();
        visited = new HashSet<>();
        visiting = new HashSet<>();
    }
    public void addEdge(int[] edge) {
        adjacencyList.computeIfAbsent(edge[0], list -> new ArrayList<>()).add(edge[1]);
    }
    
    public boolean leadsToDestination(int source, int destination) {
        if (!adjacencyList.containsKey(source)) {
            return source == destination;
        }
        if (visited.contains(source)) return true;
        if (!visiting.add(source)) return false;
        boolean ans = adjacencyList.get(source)
            .stream()
            .allMatch(neighbour -> leadsToDestination(neighbour, destination));
        visiting.remove(source);
        visited.add(source);
        return ans;
    }
}

class Node {
    int id;
    State state;
    Node(int id) {
        this.id = id;
        state = State.BLANK;
    }
    
    public static enum State {
        VISITED, BLANK, VISITING;
    }
}
