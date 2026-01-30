public class Constructor{
    int a;
    String b;
    Constructor(){
        // int a = 03;
        System.out.println("I am Constructor");
        System.out.println("I was called automatically");
    }
    public static void main(String[] args){
        Constructor obj = new Constructor();
        System.out.println(obj.a+obj.b);
    }
}