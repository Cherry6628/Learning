package search;

import java.util.*;

public class Node {
    public int id;
    public String name;
    public List<Edge> edges = new ArrayList<>();

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
