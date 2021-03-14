class Solution {
    public boolean equationsPossible(String[] equations) {
        Graph graph = new Graph();
        Connection connection = Connection.getInstance();
        return !Arrays.stream(equations)
            .anyMatch(equation -> {
                connection.init(equation);
                return !graph.canAddConnection(connection);
                });
    }
}

class Graph {
    Map<String, Node> stringToNode;
    
    Graph() {
        stringToNode = new HashMap<>();
    }
    public Node createOrGetNode(String string) {
        stringToNode.putIfAbsent(string, new Node(string));
        return stringToNode.get(string);
    }
    
    public boolean canAddConnection(Connection connection) {
        Node node1 = createOrGetNode(connection.getVariable1());
        Node node2 = createOrGetNode(connection.getVariable2());
        Node parent1 = node1.getParent();
        Node parent2 = node2.getParent();
        if (connection.getRelation() == Connection.Relation.EQUAL && parent1 != parent2) {
            parent1.setParent(parent2);
        } else if (connection.getRelation() == Connection.Relation.NOT_EQUAL && parent1 == parent2) return false;
        return true;
    }
}

class Connection {
    private static Connection instance = new Connection();
    private Connection() {
        
    }
    public static Connection getInstance() {
        return instance;
    }
    private String variable1;
    private String variable2;
    private Relation relation;
    
    public static enum Relation {
        EQUAL, NOT_EQUAL;
    }
    public String getVariable1() {
        return variable1;
    }
    public String getVariable2() {
        return variable2;
    }
    public Relation getRelation() {
        return relation;
    }
    
    public void init(String string) {
        int equalSignIndex = string.indexOf('=');
        int offset = 0;
        if (string.indexOf('!') != -1) {
            relation = Relation.NOT_EQUAL;
            offset = 1;
        }
        else relation = Relation.EQUAL;
        variable1 = string.substring(0, equalSignIndex - offset);
        variable2 = string.substring(equalSignIndex + 2 - offset);
    }
}

class Node {
    String string;
    Node parent;
    
    Node(String string) {
        this.string = string;
        parent = this;
    }
    
    public Node getParent() {
        if (this.parent == this) return this;
        this.parent = this.parent.getParent();
        return this.parent;
    }
    public void setParent(Node newParent) {
        parent = newParent;
    }
}
