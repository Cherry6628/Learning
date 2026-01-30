package DSA;

public class SinglyLinkedList {
	private class Node{
		private Object data;
		private Node next;
		Node(Object data, Node next){
			this.data=data;
			this.next=next;
		}
		Node(Object data){
			this(data, null);
		}
		Object data() {return data;}
		public String toString() {
			return this.data().toString();
		}
	}
	private Node HEAD;
	SinglyLinkedList(Object data){
		this.HEAD = new Node(data);
	}
	SinglyLinkedList(){
		this.HEAD = null;
	}
	
	void insertAtHead(Object data) {
		Node h = new Node(data);
		h.next=this.HEAD;
		this.HEAD = h;
	}
	public String toString() {
		Node temp=this.HEAD;
		if(temp==null) {
			return "[]";
		}
		StringBuffer sb = new StringBuffer();
		sb.append("["+temp);
		while(temp.next!=null) {
			temp=temp.next;
			sb.append(", "+temp);
			
		}
		sb.append("]");
		return sb.toString();
	}
	void insertAtTail(Object data) {
		Node newNode = new Node(data);
		if(this.HEAD==null) {
			this.HEAD=newNode;
			return;
		}
		Node temp = this.HEAD;
		while(temp.next!=null) {
			temp=temp.next;
		}
		temp.next = newNode;
	}
	void insertAtIndex(int n) {
		// int i
	}
	void insertAfterNode(Node node) {
		
	}
//	SinglyLinkedList(){
//		
//	}
	
	
	public static void main(String[] args) {
		SinglyLinkedList l = new SinglyLinkedList(345);
		
		System.out.println(l);
		l.insertAtTail(4);
		System.out.println(l);
		for(int i=0;i<10;i++)l.insertAtTail(i);
		System.out.println(l);
	}
}
