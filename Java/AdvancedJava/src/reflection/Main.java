package reflection;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.lang.reflect.Modifier;


class Node{
	Node left;
	Node right;
	int data;
	Node(int d){
		this.data=d;
	}
	public String toString() {
		return ""+data;
	}
	
}

public class Main {
	public static void inorder(Node n) {
		if(n==null)return;
		inorder(n.left);
		System.out.println(n);
		inorder(n.right);
	}
	public static void preorder(Node n) {
		if(n==null)return;
		System.out.println(n);
		preorder(n.left);
		preorder(n.right);
	}
	public static void postorder(Node n) {
		if(n==null)return;
		postorder(n.left);
		postorder(n.right);
		System.out.println(n);
	}
	public static void inorderStack(Node n) {
		ArrayDeque<Node> ns = new ArrayDeque<>();
		while(n!=null) {
			while(n.left!=null) {
				ns.offer(n);
				n = n.left;
			}
			System.out.println(n);
			n = ns.pollLast();
			if(n==null)break;
			System.out.println(n);
			n=n.right;
		}
		
	}
	public static void main(String[]args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException 
	{
//		Node root = new Node(1);
//		root.left = new Node(2);
//		root.right = new Node(3);
//		root.left.left = new Node(4);
//		root.left.right = new Node(5);
//		
//		
//		inorder(root);
//		System.out.println(" ");
//		
//		preorder(root);
//		System.out.println(" ");
//		
//		postorder(root);
//		System.out.println(" ");
//		
//		
//		inorderStack(root);
		
		
		
		reflector();
	}
	
	
	
	public static void reflector() throws 
		NoSuchFieldException, 
		SecurityException, 
		IllegalArgumentException, 
		IllegalAccessException 
	{
//		ArrayList<Boolean> list = new ArrayList<>();
		
		
		Class<?> c = ArrayList.class;
		for (Field f: c.getDeclaredFields()) {
			System.out.println("Field: " + f.getName());
            System.out.println("Modifiers: " + Modifier.toString(f.getModifiers()));
            System.out.println("\n");
		}
		
		Field f = c.getDeclaredField("DEFAULTCAPACITY_EMPTY_ELEMENTDATA");
		f.setAccessible(true);
//		System.out.println(f.get(null));
		
	}
}
