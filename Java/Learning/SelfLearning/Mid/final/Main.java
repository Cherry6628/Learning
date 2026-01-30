class A{
    final void disp(){
        System.out.println("Class A");
    }
}
final class B extends A{
    // @Override
    // void disp(){  // cannot be overriden
    //     System.out.println("Class B");
    // }
}
// class C extends B{  // throws error.  cannot inherit from final class

// }
public class Main{

    public static void main(String[] args){
        final int a;
        a=20;
        // a=10;  // throws error.  because final varible cannot be modified after initialization
        System.out.println(a);
    }
}