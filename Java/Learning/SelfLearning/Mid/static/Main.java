class Student{
    static int mark = 0;  // static values are not specific to objects.  They are depended on class
    // can be accessed without creating any objects
    static void display(){System.out.println("DISPLAY");}

}
public class Main{
    static{  // executed only when the class loads.
    // not executed for every objects
        System.out.println("This Class was loaded");
    }

    static int a = 9;
    int b = 10;
    public static void main(String[] args){
        Student s1 = new Student();
        s1.mark=67;
        Student s2 = new Student();
        s2.mark=50;
        System.out.println(s1.mark);  // 50
        System.out.println(s2.mark);  // 50
        System.out.println(Student.mark);  // 50
        Student.display();

        // no objects needed to access static
        System.out.println(a);

        System.out.println((new Main()).b);


        
    }
}


/*
public class Function{
    public static void main(String[] args){
        Function func = new Function();
        // main is a static method, greeting is a non-static method
        // cannot call nonstatic method from a static method without creating an object
        func.greeting();
    }
    void greeting(){
        System.out.println("Hello !");
        dummy();
        // both dummy and greeting are nonstatic method, so dummy can be called without error;
    }
    void dummy(){
        System.out.println("Dummy!");
        dummy2();
        // non static method can call static method - not a good practice
        // static method cannot call nonstatic method without creating an object
    }

    static void dummy2(){
        System.out.println("I am Static");
    }
}
*/