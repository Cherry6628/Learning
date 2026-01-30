class Animal{
    int age = 12;
    Animal(String name){
        System.out.println("Hello, I am "+name);
    }
    void makeSound(){System.out.println("I am Making Sound");}
}
class Dog extends Animal{
    Dog(){
        super("Jimmy");
        super.makeSound();
        System.out.println(super.age);
        System.out.println("Dog");
    }
}

public class Main{
    public static void main(String[] args){
        Dog d = new Dog();

    }
}