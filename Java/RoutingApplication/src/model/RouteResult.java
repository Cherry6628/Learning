package model;

import java.util.List;
import search.Node;

public class RouteResult {
    public final List<Node> path;
    public final double totalCost;

    public RouteResult(List<Node> path, double totalCost) {
        this.path = path;
        this.totalCost = totalCost;
    }
}
