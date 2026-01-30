abstract class Animal{
    String name;
    Animal(String name){
        this.name=name;
    }
    abstract void makeSound();
}
class Dog extends Animal{
    Dog(String name){
        super(name);
    }
    @Override 
    void makeSound(){
        System.out.println(this.name+" is Barking...");
    }
}
class Cat extends Animal{
    Cat(String name){
        super(name);
    }
    @Override
    void makeSound(){
        System.out.println(this.name+" is Meowing...");
    }
}

public class abstract_class{
    public static void main(String[] args){
        // Animal a = new Animal();
        Dog d = new Dog("Jimmy");
        Cat c = new Cat("Tommy");
        d.makeSound(); c.makeSound();
    }
}