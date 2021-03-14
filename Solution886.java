class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        Graph graph = new Graph();
        Arrays.stream(dislikes)
            .forEach(dislike -> {
                graph.addEdge(dislike[0], dislike[1]);
                graph.addEdge(dislike[1], dislike[0]);
            });

        Bipartition bipartition = new Bipartition(graph);
        boolean ans = bipartition.isPossible();
        graph.getString();
        return ans;
    }
}

class Bipartition {
    Graph graph;
    Bipartition(Graph graph) {
        this.graph = graph;
    }
    public boolean isPossible() {
        return !graph.getNodes()
            .stream()
            .anyMatch(id -> !doDFS(id, graph.getOrCreatePersonWithId(id).getColor(), -1));
    }
    private boolean doDFS(int id, Person.Color color, int parent) {
        if (graph.getOrCreatePersonWithId(id).getColor() == Person.Color.NO_COLOR) {
            graph.getOrCreatePersonWithId(id).setColor(color);
        } else {
            return graph.getOrCreatePersonWithId(id).getColor() == color;
        }
        return !graph.getOrCreatePersonWithId(id).getNeighbours()
            .stream()
            .anyMatch(neighbourId -> !doDFS(neighbourId, Person.getContrastingColor(color), id));
    }
}

class Graph {
    private Map<Integer, Person> idToPerson;
    
    Graph() {
        idToPerson = new HashMap<>();
    }
    public Set<Integer> getNodes() {
        return idToPerson.keySet();
    }
    public Person getOrCreatePersonWithId(int id) {
        idToPerson.putIfAbsent(id, new Person(id));
        return idToPerson.get(id);
    }
    public void addEdge(int id1, int id2) {
        getOrCreatePersonWithId(id1).addNeighbour(id2);
        Person person1 = getOrCreatePersonWithId(id1);
        Person person2 = getOrCreatePersonWithId(id2);
    }
    public void getString() {
        getNodes()
            .forEach(i -> System.out.println(idToPerson.get(i)));
    }
}

class Person {
    private int id;
    private Color isBlue;
    private List<Integer> adjacentPersonId;
    
    Person(int id) {
        this.id = id;
        adjacentPersonId = new ArrayList<>();
        isBlue = Color.NO_COLOR;
    }
    public void setColor(Color color) {
        this.isBlue = color;
    }
    public Color getColor() {
        return this.isBlue;
    }
    
    public void addNeighbour (int neighbourId) {
        adjacentPersonId.add(neighbourId);
    }
    public List<Integer> getNeighbours() {
        return adjacentPersonId;
    }
    public static enum Color {
        BLUE, RED, NO_COLOR;
    }
    public String toString() {
        return "" +  id + "-" + isBlue + "neighbours ";
    }
    public static Color getContrastingColor (Color color) {
        if (color == Color.BLUE) return Color.RED;
        return Color.BLUE;
    }
}
