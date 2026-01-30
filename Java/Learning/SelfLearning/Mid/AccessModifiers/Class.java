class AB{
    class A{}
}

class A{}

class B extends A{
    C c = new C();
}
class C{

}

public class Class{
    public static void main(String[] a){
        AB.A n = (new AB()).new A();
    }
}