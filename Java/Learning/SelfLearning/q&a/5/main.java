class Person{
    public String name;
    protected int age;
    private int socialSecurityNumber;
    String address;
    Person(String name, int age, int socialSecurityNumber, String address){
        this.name=name;
        this.age=age;
        this.socialSecurityNumber=socialSecurityNumber;
        this.address=address;
    }
}
class Employee extends Person{
    Employee(String name, int age, int socialSecurityNumber, String address){
        super(name, age, socialSecurityNumber, address);
        System.out.println(this.name+" "+this.age+" "+this.address);
    }
}

public class main{
    public static void main(String[] args){
        Employee e = new Employee("Kumar", 26, 1234567890, "Dubai");
        // e.main();
    }
}