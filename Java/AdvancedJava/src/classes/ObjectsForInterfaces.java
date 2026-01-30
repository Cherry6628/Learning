package classes;
interface ObjectInterface{
	void main();
	void main2();
}

public class ObjectsForInterfaces {
	public static void main(String[]args) {
		ObjectInterface obj = new ObjectInterface() {

			@Override
			public void main() {
				System.out.println("Hello");
			}
			
			@Override
			public void main2() {
				System.out.println("Hello 2");
				main3();
			}
			
			public void main3() { 
				// Cannot be accessed outside, but can be accessed from inside the class.
				// it is because of upcasting
				System.out.println("Hello 3");
			}
			
		};
		System.out.println(obj); // classes.ObjectsForInterfaces$1@6d06d69c
		/*
		 * Here there is no object created for the interface itself.
		 * Another anonymous class was created and was referenced from the Interface
		 * that is the reason for that "$1" in the output of printing the object.  also
		 * that is the reason for being unable to call main3 outside (upcasting allows 
		 * to call only the overriden methods. or the parents own methods) 
		*/
		
		obj.main();
		obj.main2();
//		obj.main3();  // throws error
	}
}
