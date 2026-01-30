package ticket;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

import db.Queries;
import log.LogService;
import search.RouteEdge;

public class TicketService {

    public int book(int userId, String from, String to, String transport, LocalDateTime travelTime, List<RouteEdge> path) throws Exception {


        double totalPrice = path.stream().mapToDouble(e -> e.cost).sum();

        Queries.BOOK_NEW_TICKET.setInt(1, userId);
        Queries.BOOK_NEW_TICKET.setString(2, from);
        Queries.BOOK_NEW_TICKET.setString(3, to);
        Queries.BOOK_NEW_TICKET.setString(4, transport);
        Queries.BOOK_NEW_TICKET.setDouble(5, totalPrice);
        Queries.BOOK_NEW_TICKET.setObject(6, travelTime);
        Queries.BOOK_NEW_TICKET.executeUpdate();

        ResultSet rs = Queries.BOOK_NEW_TICKET.getGeneratedKeys();
        rs.next();
        int ticketId = rs.getInt(1);

        insertRoutes(ticketId, path);

        return ticketId;
    }

    private void insertRoutes(int ticketId, List<RouteEdge> path) throws Exception {
    	Queries.GET_TRANSPORT_TYPE.setInt(1, ticketId);
    	ResultSet rs = Queries.GET_TRANSPORT_TYPE.executeQuery();
        rs.next();
       

        for (RouteEdge e : path) {
        	Queries.INSERT_TICKET_ROUTES.setInt(1, ticketId);
        	Queries.INSERT_TICKET_ROUTES.setString(2, e.from);
        	Queries.INSERT_TICKET_ROUTES.setString(3, e.to);
        	Queries.INSERT_TICKET_ROUTES.setDouble(4, e.cost);
        	Queries.INSERT_TICKET_ROUTES.setString(5, rs.getString(1));
        	Queries.INSERT_TICKET_ROUTES.addBatch();
        }

        Queries.INSERT_TICKET_ROUTES.executeBatch();
    }
    public static boolean printTickets(int userId) throws Exception {

		Queries.SELECT_BOOKED_TICKETS.setInt(1, userId);
		ResultSet rs = Queries.SELECT_BOOKED_TICKETS.executeQuery();

		boolean found = false;
		while (rs.next()) {
			found = true;
			int ticketId = rs.getInt("id");
			double totalPrice = rs.getDouble("total_price");
			String status = rs.getString("status");
			LocalDateTime ldt = rs.getObject("travel_time", LocalDateTime.class);


			Queries.SELECT_TICKET_ROUTES.setInt(1, ticketId);
			ResultSet rs2 = Queries.SELECT_TICKET_ROUTES.executeQuery();

			StringBuilder route = new StringBuilder();
			String transportType = "";
			String lastToPlace = "";

			while (rs2.next()) {
				if (route.length() > 0)
					route.append(" -> ");
				route.append(rs2.getString("from_place"));
				lastToPlace = rs2.getString("to_place");
				transportType = rs2.getString("transport_type");
			}

			if (route.length() > 0) {
				route.append(" -> ").append(lastToPlace);
			}

			System.out.println("ID: "+ticketId + " | " + route + " | " + transportType + " | Price: " + totalPrice + " | " + ldt);

		}

		if (!found)
			System.out.println("No tickets found");
		return found;

	}

	public static void cancelTicket(int ticketId, int userId) throws Exception {
		Queries.CANCEL_BOOKED_TICKET.setInt(1, ticketId);
		Queries.CANCEL_BOOKED_TICKET.setInt(2, userId);

		int rows = Queries.CANCEL_BOOKED_TICKET.executeUpdate();
		if (rows == 0) {
			System.out.println("Invalid ticket ID");
		} else {
			LogService.info("Ticket ["+ticketId+"] cancelled by User ["+userId+"]");
			System.out.println("Ticket cancelled");
		}
	}
}
