package assignments.assignment007.supportTicketProcessing;
import java.util.Arrays;
import java.util.PriorityQueue;


record Ticket(int id, String empName, String description) implements Comparable<Ticket>{
	public int compareTo(Ticket t) {
		if (t==null)return -1;
		int l1 = this.description.length(),l2 = t.description.length();
		if(l1<l2)return -1;
		if(l1>l2)return 1;
		if(this.id<t.id)return -1;
		if(this.id>t.id)return 1;
		return 0;
		
	}
	public String toString() {
		return "Ticket@("+this.id+")#[["+this.description+"]]";
	}
}
class Desk{
	PriorityQueue<Ticket> records = new PriorityQueue<>();
	private int ticketCount=0;
	private boolean raiseTicket(Ticket t) {
		try{
			if (t==null)return false;
			records.add(t);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	boolean raiseTicket(String empName, String descName) {
		if(empName.isBlank() || descName.isBlank())return false;
		return raiseTicket(new Ticket(ticketCount++, empName, descName));
	}
	String[] getNameOrder() {
		String[] names = new String[this.ticketCount];
		PriorityQueue<Ticket> tempQueue = new PriorityQueue<>(records);
		int i = 0;
		while (!tempQueue.isEmpty())
			names[i++] = tempQueue.poll().empName();
		return names;
	}
	Ticket[] getTicketsBasedOnPriorities() {
		PriorityQueue<Ticket> tempQueue = new PriorityQueue<>(records);
		int i = 0;
		Ticket[] tickets = new Ticket[ticketCount];
		while (!tempQueue.isEmpty())
		    tickets[i++] = tempQueue.poll();
		return tickets;
	}
	Ticket nextTicketToWorkOn() {
		return records.peek();
	}

}

public class Main {
	public static void main(String[]args) {
//		PriorityQueue pq = new PriorityQueue();
		Desk desk = new Desk();
		desk.raiseTicket("Sanjeevi", 		"Query not to do it");//0
		desk.raiseTicket("Sanjeevi 2.o", 	"Query to sleep");    //1
		desk.raiseTicket("Sanjeevi 3.o", 	"Query to do it");    //2
		
		System.out.println(Arrays.toString(desk.getNameOrder()));
		System.out.println("");
		
		System.out.println(Arrays.toString(desk.getTicketsBasedOnPriorities()));
		System.out.println("");

		Ticket next = desk.nextTicketToWorkOn();
		System.out.println("Next Ticket to Look into: "+next);
		System.out.println("Next Ticket's Raised Customer name: "+next.empName());

	}
}