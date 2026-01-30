class Person{
    String name;
    Person(String name){
        this.name = name;
        System.out.println("Hi, I am "+this.name);
    }
}
class Employee extends Person{
    String employeeId;
    Employee(String name){
        super(name);
    }
    void display(){
        System.out.println("My name is "+this.name+"\nMy Employee Id is "+this.employeeId);
    }
}

public class Main2{
    public static void main(String[] args){
        Employee e = new Employee("Sanjeevi");
        e.display();
        e.employeeId = "ZSTTK426/25";
        e.display();
    }
}