class Animal{  // Super Class or Base Class
    void eat(){
        System.out.println("This Animal can Eat.");
    }
}
class Dog extends Animal{  // Derived Class
    void bark(){
        System.out.println("Dog Barks");
    }
}


public class MainClass{
    public static void main(String[] args){
        Dog d1 = new Dog();
        d1.bark();
        d1.eat();
    }
}