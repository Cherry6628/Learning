class Animal{  // super-class
    void eat(){System.out.println("eating Animal");}
}
class Dog extends Animal{  // subclass
    void bark(){System.out.println("barking Dog");}
}
class Cat extends Animal{  // subclass
    void meow(){System.out.println("meow Cat");}
}

// single base class having multiple derived classes - Hierarchical
public class MainClass{
    public static void main(String[] args){
        Dog d1 = new Dog();
        d1.bark(); d1.eat();

        Cat c1 = new Cat();
        c1.meow(); c1.eat();
        
    }
}