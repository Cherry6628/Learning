package search;

import model.TransportType;

public class Edge {
    public Node to;
    public double cost;
    public TransportType type;

    public Edge(Node to, double cost, TransportType type) {
        this.to = to;
        this.cost = cost;
        this.type = type;
    }
}
