public class ConstructorOverloading{

    int a;
    String b;
    
    ConstructorOverloading(int integer, String str){
        a = integer;
        b = str;
        System.out.println("Values Passed");
    }
    ConstructorOverloading(){
        System.out.println("Nothing Passed");
    }
    public static void main(String[] args){
        ConstructorOverloading obj = new ConstructorOverloading(10, "asdf");
        System.out.println(obj.a+" "+obj.b);
        ConstructorOverloading obj2 = new ConstructorOverloading();
        System.out.println(obj2.a+" "+obj2.b);
    }
}