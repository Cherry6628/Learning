// public - can be accessed from anywhere
// private - can be accessed only inside the class
// protected - can be accessed only within same package or subclass from other packages
// (Default) -  can be accessed within the same package

package package_one;
import package_two.Teacher;
class Student{
    public String name = "San";
    private int salary = 10000;
    void showSalary(){
        System.out.println(this.salary);  // private can be accessed within the class.
    }
}

public class Main extends Teacher{
    public static void main(String[] args){
        System.out.println("Hello");
        Student s = new Student();
        System.out.println(s.name);
        //s.salary cannot be accessed (salary has private access in Student)
        s.showSalary();

        Teacher t1 = new Teacher();
        // System.out.println(t1.salary); // cannot be accessed.  default variable from another package
        // System.out.println(t1.special_material); // cannot be accessed.  protected variable from another package
        Main m = new Main();
        System.out.println(m.special_material);


    }
}