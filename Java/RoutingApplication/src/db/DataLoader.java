package db;


import java.sql.ResultSet;

import model.TransportType;
import search.Edge;
import search.Graph;
import search.Node;

public class DataLoader {
	public static Graph loadGraph() throws Exception {
		Graph g = new Graph();
		ResultSet rs = Queries.SELECT_LOCATIONS.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			g.nodes.put(id, new Node(id, name));
		}
		rs = Queries.LOAD_CITY_ROUTES.executeQuery();
		while (rs.next()) {
			Node from = g.nodes.get(rs.getInt("from_id"));
			Node to = g.nodes.get(rs.getInt("to_id"));
			double price = rs.getDouble("price");
			TransportType type = TransportType.from(rs.getString("type"));
			from.edges.add(new Edge(to, price, type));
		}
		return g;
	}
}
