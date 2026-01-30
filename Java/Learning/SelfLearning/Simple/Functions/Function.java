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
        // non static method can call static method (not a good practice.  refer "static keyword")
        // static method cannot call nonstatic method without creating an object
    }

    static void dummy2(){
        System.out.println("I am Static");
    }
}