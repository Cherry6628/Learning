interface Animal{  // cannot create any objects for interfaces
    int mark = 20;  // public static final
    void sound();
    default void display(){
        System.out.println("HELLO");
    }
    static void display2(){
        System.out.println("HELLO");
    }
}
class Cat implements Animal{
    void sound(){System.out.println("MEOW");}
}

public class main{
    public static void main(String[] args){
        

    }
}