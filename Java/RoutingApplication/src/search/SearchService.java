package search;

import java.util.*;

import model.TransportType;

public class SearchService {

    private final Graph graph;

    public SearchService(Graph g) {
        this.graph = g;
    }

    public List<RouteEdge> search(String from, String to, TransportType type) {
        from = from.strip().toLowerCase();
        to = to.strip().toLowerCase();

        Node src = resolve(from);
        Node dst = resolve(to);
        for (Node n : graph.nodes.values()) {
            if (n.name.strip().toLowerCase().equals(from)) src = n;
            if (n.name.strip().toLowerCase().equals(to)) dst = n;
        }

        if (src == null || dst == null) return null;

        Map<Node, Double> dist = new HashMap<>();
        Map<Node, Node> parent = new HashMap<>();

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(dist::get));

        for (Node n : graph.nodes.values()) dist.put(n, Double.MAX_VALUE);
        dist.put(src, 0.0);
        pq.add(src);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Edge e : cur.edges) {
                if (e.type != type) continue;
                double nd = dist.get(cur) + e.cost;
                if (nd < dist.get(e.to)) {
                    dist.put(e.to, nd);
                    parent.put(e.to, cur);
                    pq.add(e.to);
                }
            }
        }

        if (!parent.containsKey(dst)) return null;

        List<RouteEdge> path = new ArrayList<>();
        Node cur = dst;
        while (cur != src) {
            Node p = parent.get(cur);
            double cost = 0;
            for (Edge e : p.edges) if (e.to == cur && e.type == type) cost = e.cost;
            path.add(new RouteEdge(p.name, cur.name, cost));
            cur = p;
        }

        Collections.reverse(path);
        return path;
    }
    private Node resolve(String input) {
        if (input.matches("\\d+")) {
            return graph.nodes.get(Integer.parseInt(input));
        }

        String name = input.toLowerCase();
        for (Node n : graph.nodes.values()) {
            if (n.name.toLowerCase().equals(name))
                return n;
        }
        return null;
    }
}
