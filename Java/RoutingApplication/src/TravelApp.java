import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import auth.User;
//import db.DB;
import db.DataLoader;
import db.Queries;
import log.LogService;
import model.TransportType;
import search.Graph;

//import search.Node;

import search.RouteEdge;
import search.SearchService;

import ticket.TicketService;
import validate.Validator;

public class TravelApp {

	private final int userId;
	private final User user;

	private final SearchService searchService;

	private List<RouteEdge> lastSearch;
	private TransportType lastType;

	public TravelApp(User user) throws Exception {
		this.user=user;
		this.userId = user.userId();
		Graph g = DataLoader.loadGraph();
		LogService.debug("Graph Loaded Successfully");
		this.searchService = new SearchService(g);

	}

	public void menu() throws Exception {

		while (true) {

//			System.out.println("\n1.Search\n2.Book\n3.View Tickets\n4.Cancel Ticket\n5.Exit");
//			System.out.print("Choice: ");
//			String choice = Main.sc.nextLine().strip();
			int c = Main.displayOptions("Menu", new String[] {"Profile","Search", "Book", "View Tickets", "Cancel Tickets", "Exit"}, 2, true);

			switch (c) {
			case 1:
				user.displayProfile();
				break;
			case 2:
				search();
				break;
			case 3:
			    lastSearch = null;
			    search();

			    if (lastSearch != null) {
			        System.out.print("Enter travel date (yyyy-MM-dd HH:mm): ");
			        String input = Main.sc.nextLine();

			        LocalDateTime ldt = Validator.parseDate(input);

			        if (ldt == null) {
			            System.out.println("Invalid date format. Booking cancelled.");
			            break;
			        }

			        book(ldt);
			    }
			    break;
			case 4:
				view();
				break;
			case 5:
				if (view())
					cancel();
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid choice");
			}
			System.out.println("Enter to continue ... ");
			Main.sc.nextLine();
		}
	}

	private void search() throws Exception {
		String from, to;
		int limit=4;
		do {
			System.out.print("From (l to List) (e to Exit): ");
			from = Main.sc.nextLine().strip().toLowerCase();
			if("e".equals(from)) {
				return;
			}
			if("l".equalsIgnoreCase(from)) {
				ResultSet rs = Queries.SELECT_LOCATIONS.executeQuery();
				int shown=0;
				while (rs.next()) {
					shown++;
					if(shown>=limit) {
						System.out.print(shown+" "+rs.getString("name")+" ");
						Main.sc.nextLine();
						continue;
					}
					System.out.println(shown+" "+rs.getString("name"));
				}
			}
		}while(from.equals("l")||from.isEmpty()||from.isBlank());

		do {
			System.out.print("To (l to List) (e to Exit): ");
			to = Main.sc.nextLine().strip().toLowerCase();
			if("e".equals(to)) {
				return;
			}
			if("l".equalsIgnoreCase(to)) {
				ResultSet rs = Queries.SELECT_LOCATIONS.executeQuery();
				int shown=0;
				while (rs.next()) {
					shown++;
					if(shown>=limit) {
						System.out.print(shown+" "+rs.getString("name")+" ");
						Main.sc.nextLine();
						continue;
					}
					System.out.println(shown+" "+rs.getString("name"));
				}
			}
		}while(to.equals("l")||to.isEmpty()||to.isBlank());
		
		if (from.equals(to)) {
			System.out.println("Invalid Input");
			return;
		}
		System.out.print("Transport (road/train/flight): ");
		String t = Main.sc.nextLine().strip();
		TransportType type = TransportType.from(t);
		if (type == null) {
			System.out.println("Invalid transport type");
			return;
		}
		lastSearch = searchService.search(from, to, type);
		lastType = type;
		if (lastSearch == null || lastSearch.isEmpty()) {
			System.out.println("No route found");
			lastSearch = null;
			return;
		}

		double totalCost = 0;
		for (RouteEdge e : lastSearch)
			totalCost += e.cost;
	
		System.out.print("Route: ");
		printRoute(lastSearch);
	    System.out.print(" | Transport: " + lastType);
		System.out.println(" | Total cost: " + totalCost);

//		System.out.println("Route found. Total cost: " + totalCost);
	}
	private void printRoute(List<RouteEdge> result) {
	    if (result.isEmpty()||result==null) {
	        System.out.println("No route");
	        return;
	    }

	    StringBuilder sb = new StringBuilder();

	    sb.append(result.get(0).from);

	    for (RouteEdge e : result) {
	        sb.append(" -> ").append(e.to);
	    }

	    System.out.print(sb.toString());
	}

	private void book(LocalDateTime ldt) throws Exception {
		if (lastSearch == null || lastSearch.isEmpty() || lastType == null) {
			System.out.println("Search a route before booking");
			return;
		}
		TicketService ts = new TicketService();
		String fromPlace = lastSearch.get(0).from;
		String toPlace = lastSearch.get(lastSearch.size() - 1).to;
		String transport = lastType.name();
		double totalCost = lastSearch.stream().mapToDouble(e -> e.cost).sum();
		int ticketId = ts.book(userId, fromPlace, toPlace, transport, ldt, lastSearch);
		LogService.info(String.format("User [%s] booked Ticket [%s]. Price: %.2f", userId, ticketId, totalCost));
		System.out.println("Ticket booked successfully! Total: " + totalCost + " | Ticket ID: " + ticketId);
		lastSearch = null;
	}

	private boolean view() throws Exception {
		return TicketService.printTickets(userId);
	}

	private void cancel() throws Exception {

		System.out.print("Enter Ticket ID to cancel: ");
		String s = Main.sc.nextLine().strip();

		if (!s.matches("\\d+")) {
			System.out.println("Invalid ticket id");
			return;

		}
		int ticketId = Integer.parseInt(s);

		TicketService.cancelTicket(ticketId, userId);

	}

}
