public class Teacher{
    String name;
    String id;
    String addr;
    Teacher(){
        System.out.println("Hello ! I am Teacher !");
    }
    void setName(String name, String myId, String addr){
        // System.out.println("Display");
        name = name; //'this.name' was not modified, because parameter 'name' was used instead of 'this.name', since both has same name
        id = myId; //'this.id' was modified
        this.addr = addr;  // 'addr' will not work if used, and will simply gives null when t1.addr is printed.  but here this keyword is used, so this one directly changes the addr attribute of the object
    }   // try removing this 'this.' and compiling the code.  you get null for the addr attribute
    public static void main(String[] args){
        Teacher t1 = new Teacher();
        t1.setName("Mark", "asdf", "My Address");
        System.out.println(t1.name+" "+t1.id+" "+t1.addr); // gives null
    }

}