package DSA;


public class DoublyLinkedList<T> {
	private class Node{
		private T data;
		private Node next;
		private Node prev;
		Node(T data, Node prev, Node next){
			this.data=data;
			this.prev=prev;
			this.next=next;
		}
		Node(T data, Node prev){
			this(data, prev, null);
		}
		Node(T data){
			this(data, null);
		}
		
		T data() {return data;}
		public String toString() {
			return "("+(this.prev!=null?this.prev.data():null)+")"+this.data()+"("+(this.next!=null?this.next.data():null)+")";
//			return this.data().toString();
		}
	}
	private Node HEAD;
	private Node TAIL;
	private int size=0;
	DoublyLinkedList(T data){
		this.HEAD = new Node(data);
		this.TAIL = this.HEAD;
	}
	DoublyLinkedList(){
		this.HEAD = null;
		this.TAIL = null;
	}
	int size() {return size;}
	void insertAtHead(T data) {
		Node h = new Node(data);
		if(this.HEAD==null) {
			this.HEAD=h;
			this.TAIL=h;
			return;
		}
		h.next=this.HEAD;
		this.HEAD.prev=h;
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
	void insertAtTail(T data) {
		Node h = new Node(data);
		if(this.TAIL==null) {
			this.HEAD=h;
			this.TAIL=h;
			return;
		}
		this.TAIL.next = h;
		this.TAIL.next.prev = this.TAIL;
		this.TAIL = this.TAIL.next;
	}
	void insertAtIndex(T data) {
		insertAtIndex(data, 0);
	}
	void insertAtIndex(T data, int n) {
//		Node cur = this.HEAD;
//		while(cur!=null)
	}
	void insertAfterNode(Node node) {
		
	}
//	SinglyLinkedList(){
//		
//	}
	
	
	public static void main(String[] args) {
		DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
		
		System.out.println(l);
		l.insertAtHead(4);
//		System.out.println(l);
		for(int i=0;i<10;i++)l.insertAtTail(i);
		System.out.println(l);
	}
}

