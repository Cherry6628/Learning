package assignments.assignment004;
import java.util.ArrayList;
import java.util.Scanner;
class Phrase{
	private String value;
	Phrase(String value){
		this.value=value!=null?value:"";
	}
	Phrase(){
		this("");
	}
	Phrase(Phrase word){
		this(word!=null?word.toString():"");
	}
	public String toString() {return this.value;}
	public boolean equals(Phrase w) {
//		return false;
		if (this==w)return true;
		if (w==null)return false;
		return (this.value==w.value || this.value.equals(w.value));
//		return false;
	}
	public boolean equals(String s) {
		if (this.value==s)return true;
		return this.value.equals(s);
	}
	public boolean equals(Object o) {
		return this.equals((Phrase)o);
	}
}

class VersionTrackerText {
	private ArrayList<Phrase> prev = new ArrayList<>();
	private ArrayList<Phrase> next = new ArrayList<>();
	private Phrase cur;
	VersionTrackerText(Phrase word){
		this.cur=word!=null?word:new Phrase();
	}
	VersionTrackerText(String word){
		this(new Phrase(word));
	}
	VersionTrackerText(){
		this("");
	}
	public String toString() {
		return this.cur.toString();
//		return prev.toString()+" "+this.cur+" "+this.next.toString();
	}
	private boolean addVersion(Phrase w, ArrayList<Phrase> stack) {
		try{
			if (stack.size()==0 || !stack.getLast().equals(w)) {
//				if(stack.size()!=0)System.out.println(stack.getLast()+" "+w+" "+stack.getLast().equals(w));
				stack.add(w);
				return true;
			}
			return false;
		}
		catch(Exception e) {e.printStackTrace();return false;}
	}
	private Phrase removeVersion(ArrayList<Phrase> stack) {
		if (stack.size()==0)return null;
		return new Phrase(stack.removeLast());
	}
//	private boolean hasText(ArrayList<Phrase> stack) {
//		return stack.getLast().toString().length()!=0;
//	}
	public boolean update(String w) {
		return this.update(new Phrase(w));
	}
	public boolean update(Phrase w) {
		try {
			if(this.cur.equals(w))return false;
			if (!this.addVersion(this.cur, prev)) {
				return false;
			}
			this.cur=w;
			this.next.clear();
			return true;
		} catch(Exception e){e.printStackTrace();return false;}
	}
	public boolean undo() {
		try {
			Phrase temp = this.removeVersion(prev);
			if (temp==null || !this.addVersion(this.cur, next)) {
				if(temp!=null)this.addVersion(temp, prev);
				return false;
			}
			this.cur = temp;
			return true;
		}catch(Exception e) {return false;}
	}
	public boolean redo() {
		try {
			Phrase temp = this.removeVersion(next);
			if (temp==null || !this.addVersion(this.cur, prev)) {
				if(temp!=null)this.addVersion(temp, next);
				return false;
			}
			this.cur = temp;
			return true;
		}catch(Exception e) {return false;}
	}
}

public class TextEditor{
	public static void main(String main[]) {
		Scanner sc=null;
		try {
			VersionTrackerText vt = new VersionTrackerText();
			sc = new Scanner(System.in);
			
			System.out.println("New Editor: ");
			while(true) {
				System.out.print(vt);
				vt.update(vt.toString()+sc.nextLine());
				System.out.print("\n\n\n\n[0] - Continue Editing [1] - Undo\t[2] - Redo\t[3] - Exit\n>> ");
				int i;
				try{
					i=sc.nextInt();
				}catch(Exception e) {i=-1;}
				sc.nextLine();
				if (i==1) {
					vt.undo();
				} else if (i==2) {
					vt.redo();
				} else if (i==3) {
					break;
				} else if(i!=0){
					System.out.print("Invalid Option.\n");
					sc.nextLine();
				}
				System.out.println("\033[H\033[2JEditor Interface: ");
			}
			System.out.println("Thank you for using the application.");
			
			
			
			
		}
		finally {
			if(sc!=null)sc.close();
		}
	}
}
