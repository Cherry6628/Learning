package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {
	public static PreparedStatement SELECT_LOCATIONS = null;
	public static PreparedStatement LOAD_CITY_ROUTES = null;

	public static PreparedStatement INSERT_NEW_USER = null;
	public static PreparedStatement SELECT_USER = null;

	public static PreparedStatement SELECT_BOOKED_TICKETS = null;
	public static PreparedStatement SELECT_ONE_TICKET_ALONE = null;

	public static PreparedStatement SELECT_TICKET_ROUTES = null;
	public static PreparedStatement CANCEL_BOOKED_TICKET = null;

	public static PreparedStatement BOOK_NEW_TICKET = null;

	public static PreparedStatement INSERT_TICKET_ROUTES = null;
	public static PreparedStatement GET_TRANSPORT_TYPE = null;
//	public static PreparedStatement 

	static {
		try {
			Connection c = DB.conn();
			INSERT_TICKET_ROUTES = c.prepareStatement(
					"INSERT INTO ticket_routes (ticket_id, from_place, to_place, cost, transport_type) "
							+ "VALUES (?, ?, ?, ?, (SELECT id FROM transport_type WHERE type=?))");

			GET_TRANSPORT_TYPE = c.prepareStatement(
					"SELECT type FROM transport_type WHERE id = (SELECT transport_type FROM tickets WHERE id = ?)");

			BOOK_NEW_TICKET = c.prepareStatement(
					"INSERT INTO tickets (user_id, from_place, to_place, transport_type, total_price, status, booked_at, travel_time) "
							+ "VALUES (?, ?, ?, (SELECT id FROM transport_type WHERE type=?), ?, 'BOOKED', NOW(), ?)",
					Statement.RETURN_GENERATED_KEYS);

			SELECT_LOCATIONS = c.prepareStatement("SELECT id, name FROM locations order by id;");

			LOAD_CITY_ROUTES = c.prepareStatement("SELECT r.from_id as from_id, r.to_id as to_id, r.price as price, t.type as type "
					+ "FROM routes r JOIN transport_type t ON r.transport_type_id = t.id");

			INSERT_NEW_USER = c.prepareStatement("INSERT INTO users (uname, pwd, email) VALUES (?, ?, ?)");

			SELECT_USER = c.prepareStatement("SELECT id, uname, pwd, email FROM users WHERE uname=?");

			SELECT_BOOKED_TICKETS = c.prepareStatement(
					"SELECT id, total_price, status, travel_time FROM tickets WHERE user_id=? AND status='BOOKED' AND travel_time>NOW();");

			SELECT_TICKET_ROUTES = c.prepareStatement("SELECT tr.from_place as from_place, tr.to_place as to_place, t.type as transport_type "
					+ "FROM ticket_routes tr JOIN transport_type t ON tr.transport_type = t.id WHERE tr.ticket_id=? ORDER BY tr.id");

			CANCEL_BOOKED_TICKET = c.prepareStatement(
					"UPDATE tickets SET status='CANCELLED' WHERE id=? AND user_id=? AND status='BOOKED' AND travel_time>NOW();");
			
		} catch (SQLException e) {
		}
	}

}
