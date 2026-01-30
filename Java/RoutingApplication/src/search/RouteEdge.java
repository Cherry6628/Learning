package search;

public class RouteEdge {
    public final String from;
    public final String to;
    public final double cost;

    public RouteEdge(String from, String to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
