package assignments.assignment009;

class Library{
	private int books;
	Library(int books){
		this.books=books;
		
	}
	int getBooks() {
//		sync
			return books;
	}
	int getBooks(int i) {
		if(i>books) return 0;
		books-=i;
		return i;
	}
	
}

class Student extends Thread{
	String name;
	Library lib;
	private int c;
	Student(String name, Library lib){
		this.name=name;
		this.lib=lib;
	}
	void borrowBooks(int c) {
		this.c = c;
		this.start();
	}
	
	public void run() {
		synchronized(lib) {
			System.out.println(lib.getBooks(c)+" books borrowed by "+this.name);
			System.out.println("Library has "+lib.getBooks()+" Books");
		}
	}
}
public class Main {
	public static void main(String[]args) {
		Library lib = new Library(20);
		Student s0 = new Student("Student 0",lib);
		Student s1 = new Student("Student 1",lib);
		Student s2 = new Student("Student 2",lib);
		Student s3 = new Student("Student 3",lib);
		Student s4 = new Student("Student 4",lib);

		s0.borrowBooks(3);
		s1.borrowBooks(10);
		s2.borrowBooks(2);
		s3.borrowBooks(2);
		s4.borrowBooks(1);
		
	}
}
