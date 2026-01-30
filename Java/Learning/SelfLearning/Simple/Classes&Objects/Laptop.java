public class Laptop{
    String name;
    int price = 0;
    byte ram = 0;
    public static void main(String[] args){
        Laptop lap1 = new Laptop();
        lap1.name = "HPPP";
        lap1.price = 100000;
        lap1.ram = 4;

        
        Laptop lap2 = new Laptop();
        lap2.name = "Dell"; lap2.price = 3243423; lap2.ram = 3;

        System.out.println(lap2);
    }
}

