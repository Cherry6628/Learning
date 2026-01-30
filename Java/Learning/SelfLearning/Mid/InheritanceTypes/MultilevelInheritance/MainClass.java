

class A{
    int one = 1;
}

class B extends A{
    int two = 2;
}

class C extends B{
    int three = 3;
}

class Animal{
    void eat(){System.out.println("eating Animal");}
}
class Dog extends Animal{
    void bark(){System.out.println("barking Dog");}
}
class Puppy extends Dog{
    void weep(){System.out.println("weeping Puppy");}
}
public class MainClass{
    public static void main(String[] args){
        C c = new C();
        System.out.println(c.three);
        System.out.println(c.two);
        System.out.println(c.one);


        Puppy jim = new Puppy();
        jim.eat(); jim.weep(); jim.bark();
    }
}