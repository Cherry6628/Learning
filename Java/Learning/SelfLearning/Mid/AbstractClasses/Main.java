abstract class Vehicle{
    abstract void speed();
    void brand(){
        System.out.println("BRAND");
    }
}
class Bike extends Vehicle{
    @Override
    void speed(){
        System.out.println("50km/hr");
    }
}
class Car extends Vehicle{
    @Override
    void speed(){
        System.out.println("100km/hr");
    }
}

abstract class Plane{
    abstract void type();
}

public class Main{
    public static void main(String[] args){
        Bike b = new Bike();
        b.speed();
        b.brand();
    }
}